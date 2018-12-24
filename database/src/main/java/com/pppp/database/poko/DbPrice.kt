package com.pppp.database.poko

import android.arch.persistence.room.Entity
import com.pppp.entities.Price

@Entity
data class DbPrice(override var price: String?) :Price
