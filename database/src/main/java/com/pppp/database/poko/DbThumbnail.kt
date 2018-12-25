package com.pppp.database.poko

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.pppp.entities.Thumbnail

@Entity
data class DbThumbnail(
    @PrimaryKey
    @ColumnInfo(name = "thumb_id")
    val thumb_id: Int?,
    @ColumnInfo(name = "path")
    override val path: String?,
    @ColumnInfo(name = "extension")
    override val extension: String?
) : Thumbnail
