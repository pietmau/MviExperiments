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
import com.pppp.lib.ComicsBook
import com.pppp.lib.Creators
import com.pppp.lib.Price
import com.pppp.lib.Thumbnail


@Dao
internal abstract class ComicsDao {

    @Query("SELECT * from dbcomicsbook WHERE id=:id")
    protected abstract fun getBookByIdInternal(id: Int): DbComicsBook?

    /** Unfortunately Room does not support relationships. */
    fun getBookById(id: Int): ComicsBook? =
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

    /** no relationships.... */
    fun getAllBooks(): List<ComicsBook> =
        getAllBooksInternal().map { book ->
            book.apply {
                prices = getPrices(this.id) ?: emptyList()
                creators = getCreators(this.id)
                thumbnail = getThumbByIdInternal(this.id)
            }
        }

    fun insertAll(books: List<ComicsBook>) {
        books.forEach { insertBookInternal(it) }
    }

    /** no relationships.... */
    private fun insertBookInternal(comicsBook: ComicsBook) {
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

    private fun insertThumbInternal(thumbnail: Thumbnail?, id: Int) {
        val thumb = DbThumbnail(null, thumbnail?.path, thumbnail?.extension, id)
        insertThumb(thumb)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertThumb(thumb: DbThumbnail)

    private fun insertCreatorsInternal(creators: Creators?, comicId: Int) {
        creators?.items?.forEach { insertItem(DbItem(null, it.name, comicId)) }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertItem(dbItem: DbItem)

    private fun insertPricesInternal(prices: List<Price>?, comicId: Int) {
        prices?.forEach { price ->
            insertPrice(DbPrice(null, price.price, comicId))
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertPrice(dbPrice: DbPrice)
}