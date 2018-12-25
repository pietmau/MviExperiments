package com.pppp.database.poko

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.pppp.entities.Item

@Entity
data class DbItem(
    @PrimaryKey
    val id: Int?,
    val result_id: Int?,
    override val name: String?
) : Item

