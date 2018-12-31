package com.pppp.database.poko

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = DbComicsBook::class,
        parentColumns = ["id"],
        childColumns = ["comics_id"],
        onDelete = ForeignKey.CASCADE
    )], indices = [Index("comics_id")]
)
internal data class DbPrice(
    @PrimaryKey
    val id: Int?,
    override val price: String?,
    val comics_id: Int?
) : com.pppp.lib.Price
