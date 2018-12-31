package com.pppp.database.poko

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.pppp.lib.ComicsBook

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