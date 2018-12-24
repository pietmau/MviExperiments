package com.pppp.database.poko

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.pppp.entities.Creators

@Entity
data class DbCreators(
    @ColumnInfo(name = "items")
    override val items: List<DbItem>?) :Creators
