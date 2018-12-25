package com.pppp.database.poko

import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation
import com.pppp.entities.Creators


data class DbCreators(
    @PrimaryKey
    val creator_id: Int?
) : Creators{
    @Relation(
        parentColumn = "creator_id",
        entityColumn = "id"
    )
    override var items: List<DbItem> = emptyList()
}
