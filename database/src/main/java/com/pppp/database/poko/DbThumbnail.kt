package com.pppp.database.poko

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.pppp.entities.Thumbnail

@Entity
data class DbThumbnail(
    @ColumnInfo(name = "path")
    override var path: String?,
    @ColumnInfo(name = "extension")
    override var extension: String?) : Thumbnail
