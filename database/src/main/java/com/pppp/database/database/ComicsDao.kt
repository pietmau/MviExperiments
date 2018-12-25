package com.pppp.database.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.pppp.database.poko.*
import com.pppp.entities.Item
import com.pppp.entities.Price
import com.pppp.entities.Result

//TODO use translator
@Dao
abstract class ComicsDao {

    @Query("SELECT * from dbresult JOIN dbthumbnail ON (dbresult.id=dbthumbnail.thumb_id)")
    abstract fun getAllComics(): List<DbResultWithPrices>

    @Query("SELECT * from dbresult WHERE id=:id")
    abstract fun getComicById(id: Int?): DbResultWithPrices?

    fun insert(results: List<Result>) {
        results.forEach { insert(it) }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertResult(result: DbResult)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertThumbnail(thumbnail: DbThumbnail?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(it: DbPrice)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertItem(it: DbItem)

    private fun insert(result: Result) {
        val dbResultWithPrices = DbResultWithPrices(getResult(result), getThumb(result))
        insertResult(dbResultWithPrices.dbResult);
        insertThumbnail(dbResultWithPrices.thumbnail);
        insertPrices(getPrices(result.prices, result.id))
        insertItems(getItems(result.creators?.items, result.id))
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

    private fun getThumb(result: Result): DbThumbnail =
        DbThumbnail(result.id, result.thumbnail?.path, result.thumbnail?.extension)

    private fun getResult(result: Result): DbResult =
        DbResult(result.id, result.title, result.description, result.pageCount)

}