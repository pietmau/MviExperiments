package com.pppp.database.poko

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.pppp.entities.Thumbnail

@Entity
data class DbThumbnail(
    @PrimaryKey
    val thumb_id: Int?,
    override val path: String?,
    override val extension: String?
) : Thumbnail
