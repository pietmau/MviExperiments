package com.pppp.database.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.pppp.database.poko.DbResultWithPrices

@Dao
interface ComicsDao {

    @Query("SELECT * from dbresult")
    fun getUsersWithRepos(): List<DbResultWithPrices>
/*
    @Query("SELECT * FROM dbresult WHERE id = :id")
    fun get(id: Int): DbResultWithPrices
*/

//    @Insert
//    fun insert(result: DbResultWithPrices){
//
//    }
}