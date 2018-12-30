package com.pppp.database.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.pppp.database.poko.*
import com.pppp.entities.Item
import com.pppp.entities.Price
import com.pppp.entities.ComicsBook

//TODO use translator
@Dao
abstract class ComicsDao {

    @Query("SELECT * from dbresult JOIN dbthumbnail ON (dbresult.id=dbthumbnail.thumb_id)")
    abstract fun getAllComics(): List<DbComicsBookWithPrices>

    @Query("SELECT * from dbresult JOIN dbthumbnail ON (dbresult.id=dbthumbnail.thumb_id) WHERE id=:id")
    abstract fun getComicById(id: Int?): DbComicsBookWithPrices?

    fun insert(comicsBooks: List<ComicsBook>) {
        comicsBooks.forEach { insert(it) }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertResult(result: DbResult)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertThumbnail(thumbnail: DbThumbnail?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(it: DbPrice)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertItem(it: DbItem)

    private fun insert(comicsBook: ComicsBook) {
        val dbResultWithPrices = DbComicsBookWithPrices(getResult(comicsBook), getThumb(comicsBook))
        insertResult(dbResultWithPrices.dbResult);
        insertThumbnail(dbResultWithPrices.thumbnail);
        insertPrices(getPrices(comicsBook.prices, comicsBook.id))
        insertItems(getItems(comicsBook.creators?.items, comicsBook.id))
    }

    private fun insertPrices(prices: List<DbPrice>?) {
        prices?.forEach { insert(it) }
    }

    private fun insertItems(items: List<DbItem>?) {
        items?.forEach { insertItem(it) }
    }

    private fun getItems(items: List<Item>?, id: Int?) =
        items?.map { DbItem(null, id, it.name) }

    private fun getPrices(prices: List<Price>?, id: Int?) =
        prices?.map { DbPrice(null, it.price, id) }

    private fun getThumb(comicsBook: ComicsBook): DbThumbnail =
        DbThumbnail(comicsBook.id, comicsBook.thumbnail?.path, comicsBook.thumbnail?.extension)

    private fun getResult(comicsBook: ComicsBook): DbResult =
        DbResult(comicsBook.id, comicsBook.title, comicsBook.description, comicsBook.pageCount)

}