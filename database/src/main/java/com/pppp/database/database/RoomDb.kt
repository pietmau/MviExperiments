package com.pppp.database.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.pppp.database.poko.DbComicsBook
import com.pppp.database.poko.DbItem
import com.pppp.database.poko.DbPrice
import com.pppp.database.poko.DbThumbnail

@Database(
    entities = arrayOf(
        DbComicsBook::class,
        DbItem::class,
        DbPrice::class,
        DbThumbnail::class
    ), version = 1
)
internal abstract class RoomDb : RoomDatabase() {
    abstract fun dao(): ComicsDao

    companion object {
        fun db(context: Context): RoomDb =
            Room.databaseBuilder(context, RoomDb::class.java, "comics")
                .build()
    }
}