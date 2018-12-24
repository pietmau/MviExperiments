package com.pppp.database.poko

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.pppp.entities.Item

@Entity
data class DbItem(
    @ColumnInfo(name = "name")
    override var name: String?) : Item
