package com.pppp.database.poko

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class DbResult(
    @PrimaryKey
    val id: Int?,
    val title: String?,
    val description: String?,
    val pageCount: Int
)