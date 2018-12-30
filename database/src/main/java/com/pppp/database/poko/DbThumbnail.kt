package com.pppp.database.poko

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import com.pppp.entities.Thumbnail

@Entity(
    foreignKeys = [ForeignKey(
        entity = DbComicsBook::class,
        parentColumns = ["id"],
        childColumns = ["comic_id"],
        onDelete = CASCADE
    )]
)
internal data class DbThumbnail(
    @PrimaryKey
    val id: Int?,
    override val path: String?,
    override val extension: String?,
    val comic_id: Int?
) : Thumbnail
