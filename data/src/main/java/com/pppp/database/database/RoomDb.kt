package com.pppp.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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