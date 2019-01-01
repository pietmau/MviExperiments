package com.pppp.database.poko

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.pppp.lib.Thumbnail

@Entity(
    foreignKeys = [ForeignKey(
        entity = DbComicsBook::class,
        parentColumns = ["id"],
        childColumns = ["comics_id"],
        onDelete = CASCADE
    )], indices = [Index("comics_id")]
)
internal data class DbThumbnail(
    @PrimaryKey
    val id: Int?,
    override val path: String?,
    override val extension: String?,
    val comics_id: Int?
) : Thumbnail
