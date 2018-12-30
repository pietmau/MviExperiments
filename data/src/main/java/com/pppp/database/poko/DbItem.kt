package com.pppp.database.poko

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = DbComicsBook::class,
        parentColumns = ["id"],
        childColumns = ["comic_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
internal data class DbItem(
    @PrimaryKey
    val id: Int?,
    override val name: String?,
    val comic_id: Int?
) : com.pppp.lib.Item

