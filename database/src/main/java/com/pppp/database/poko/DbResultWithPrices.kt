package com.pppp.database.poko

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Relation
import com.pppp.entities.Result

data class DbResultWithPrices(
    @Embedded
    val dbResult: DbResult,
    @Embedded
    override val thumbnail: DbThumbnail?
) : Result {
    @Relation(
        parentColumn = "id",
        entityColumn = "result_id"
    )
    var items: List<DbItem> = emptyList()
        set(value) {
            creators = DbCreators(value)
        }
    @Ignore
    override val id = dbResult.id
    @Ignore
    override val title = dbResult.title
    @Ignore
    override val description = dbResult.description
    @Ignore
    override val pageCount = dbResult.pageCount
    @Relation(
        parentColumn = "id",
        entityColumn = "comicId"
    )
    override var prices: List<DbPrice>? = emptyList()
    @Ignore
    override lateinit var creators: DbCreators
}