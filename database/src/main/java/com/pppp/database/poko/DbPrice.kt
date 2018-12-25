package com.pppp.database.poko

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.pppp.entities.Price

@Entity
data class DbPrice(
    @PrimaryKey
    val id: Int?,
    override val price: String?,
    val comicId: Int?
) : Price
