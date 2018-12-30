package com.pppp.database.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.pppp.database.poko.*
import com.pppp.entities.ComicsBook
import com.pppp.entities.Creators
import com.pppp.entities.Price
import com.pppp.entities.Thumbnail

@Dao
internal abstract class ComicsDao {

    @Query("SELECT * from dbcomicsbook WHERE id=:id")
    protected abstract fun getBookByIdInternal(id: Int): DbComicsBook?

    fun getBookById(id: Int): ComicsBook? =
        getBookByIdInternal(id)?.apply {
            prices = getPrices(id) ?: emptyList()
            creators = getCreators(id)
            thumbnail = getThumbByIdInternal(id)
        }

    @Query("SELECT * from dbthumbnail WHERE comic_id=:id")
    abstract fun getThumbByIdInternal(id: Int): DbThumbnail?

    @Query("SELECT * from dbprice WHERE comic_id=:id")
    abstract fun getPrices(id: Int): List<DbPrice>?

    private fun getCreators(id: Int) = DbCreators(getItems(id))

    @Query("SELECT * from dbitem WHERE comic_id=:id")
    abstract fun getItems(id: Int): List<DbItem>

    @Query("SELECT * from dbcomicsbook")
    abstract fun getAllBooksInternal(): List<DbComicsBook>

    fun getAllBooks(): List<ComicsBook> =
        getAllBooksInternal().filterNotNull().map { book ->
            book.apply {
                prices = getPrices(this.id) ?: emptyList()
                creators = getCreators(this.id)
                thumbnail = getThumbByIdInternal(this.id)
            }
        }

    fun insertAll(books: List<ComicsBook>) {
        books.forEach { insertBookInternal(it) }
    }

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