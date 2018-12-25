package com.pppp.database.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.pppp.database.poko.DbResult
import com.pppp.database.poko.DbResultWithPrices
import com.pppp.database.poko.DbThumbnail
import com.pppp.entities.Result

@Dao
abstract class ComicsDao {

    @Query("SELECT * from dbresult JOIN dbthumbnail ON (dbresult.id=dbthumbnail.thumb_id)")
    abstract fun getUsersWithRepos(): List<DbResultWithPrices>

    fun insert(result: Result) {
        val r = DbResultWithPrices(getResult(result), getThumb(result))
        insert(r.dbResult);
        insert(r.thumbnail);
    }

    fun getThumb(result: Result): DbThumbnail =
        DbThumbnail(null, result.thumbnail?.path, result.thumbnail?.extension)

    fun getResult(result: Result): DbResult =
        DbResult(null, result.title, result.description, result.pageCount)


    fun insert(results: List<Result>) {
        results.forEach { insert(it) }
    }

    @Insert
    abstract fun insert(thumbnail: DbThumbnail?)

    @Insert
    abstract fun insert(result: DbResult)
}