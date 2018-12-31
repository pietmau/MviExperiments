package com.pppp.database.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pppp.database.poko.DbComicsBook
import com.pppp.database.poko.DbCreators
import com.pppp.database.poko.DbItem
import com.pppp.database.poko.DbPrice
import com.pppp.database.poko.DbThumbnail

@Dao
internal abstract class ComicsDao {

    @Query("SELECT * from dbcomicsbook WHERE id=:id")
    protected abstract fun getBookByIdInternal(id: Int): DbComicsBook?

    fun getBookById(id: Int): com.pppp.lib.ComicsBook? =
        getBookByIdInternal(id)?.apply {
            prices = getPrices(id) ?: emptyList()
            creators = getCreators(id)
            thumbnail = getThumbByIdInternal(id)
        }

    @Query("SELECT * from dbthumbnail WHERE comics_id=:id")
    abstract fun getThumbByIdInternal(id: Int): DbThumbnail?

    @Query("SELECT * from dbprice WHERE comics_id=:id")
    abstract fun getPrices(id: Int): List<DbPrice>?

    private fun getCreators(id: Int) = DbCreators(getItems(id))

    @Query("SELECT * from dbitem WHERE comics_id=:id")
    abstract fun getItems(id: Int): List<DbItem>

    @Query("SELECT * from dbcomicsbook")
    abstract fun getAllBooksInternal(): List<DbComicsBook>

    fun getAllBooks(): List<com.pppp.lib.ComicsBook> =
        getAllBooksInternal().map { book ->
            book.apply {
                prices = getPrices(this.id) ?: emptyList()
                creators = getCreators(this.id)
                thumbnail = getThumbByIdInternal(this.id)
            }
        }

    fun insertAll(books: List<com.pppp.lib.ComicsBook>) {
        books.forEach { insertBookInternal(it) }
    }

    private fun insertBookInternal(comicsBook: com.pppp.lib.ComicsBook) {
        val success = insertBook(
            DbComicsBook(
                comicsBook.id,
                comicsBook.title,
                comicsBook.description,
                comicsBook.pageCount
            )
        )
        if (success != -1L) {
            insertThumbInternal(comicsBook.thumbnail, comicsBook.id)
            insertCreatorsInternal(comicsBook.creators, comicsBook.id)
            insertPricesInternal(comicsBook.prices, comicsBook.id)
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertBook(book: DbComicsBook): Long?

    private fun insertThumbInternal(thumbnail: com.pppp.lib.Thumbnail?, id: Int) {
        val thumb = DbThumbnail(null, thumbnail?.path, thumbnail?.extension, id)
        insertThumb(thumb)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertThumb(thumb: DbThumbnail)

    private fun insertCreatorsInternal(creators: com.pppp.lib.Creators?, comicId: Int) {
        creators?.items?.forEach { insertItem(DbItem(null, it.name, comicId)) }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertItem(dbItem: DbItem)

    private fun insertPricesInternal(prices: List<com.pppp.lib.Price>?, comicId: Int) {
        prices?.forEach { price ->
            insertPrice(DbPrice(null, price.price, comicId))
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertPrice(dbPrice: DbPrice)
}