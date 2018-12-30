package com.pppp.database.poko

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.pppp.entities.Price

@Entity(
    foreignKeys = [ForeignKey(
        entity = DbComicsBook::class,
        parentColumns = ["id"],
        childColumns = ["comic_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
internal data class DbPrice(
    @PrimaryKey
    val id: Int?,
    override val price: String?,
    val comic_id: Int?
) : Price
