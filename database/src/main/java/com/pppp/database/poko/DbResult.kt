package com.pppp.database.poko

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.pppp.entities.Result

@Entity
data class DbResult(
    @PrimaryKey
    override var id: Int?,
    @ColumnInfo(name = "title")
    override var title: String?,
    @ColumnInfo(name = "first_name")
    override var description: String?,
    @ColumnInfo(name = "pageCount")
    override var pageCount: Int,

    @ColumnInfo(name = "prices")
    override val prices: List<DbPrice>?,

    @Embedded
    @ColumnInfo(name = "thumbnail")
    override val thumbnail: DbThumbnail?,

    @Embedded
    @ColumnInfo(name = "creators")
    override val creators: DbCreators?
) : Result