package com.pppp.database.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.pppp.database.poko.DbItem
import com.pppp.database.poko.DbPrice
import com.pppp.database.poko.DbResult
import com.pppp.database.poko.DbThumbnail

@Database(
    entities = arrayOf(
        DbResult::class,
        DbItem::class,
        DbPrice::class,
        DbThumbnail::class
    ), version = 1
)
abstract class RoomDb : RoomDatabase() {
    abstract fun dao(): ComicsDao

    companion object {
        fun db(context: Context): RoomDb =
            Room.databaseBuilder(context, RoomDb::class.java, "comics")
                .build()
    }
}