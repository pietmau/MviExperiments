package com.pppp.database.poko

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.pppp.entities.ComicsBook

@Entity
internal data class DbComicsBook(
    @PrimaryKey
    override val id: Int,
    override val title: String?,
    override val description: String?,
    override val pageCount: Int
    ) : ComicsBook {
    @Ignore
    override var prices: List<DbPrice> = emptyList()
    @Ignore
    override var thumbnail: DbThumbnail? = null
    @Ignore
    override var creators: DbCreators? = null
}