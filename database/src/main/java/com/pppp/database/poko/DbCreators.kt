package com.pppp.database.poko

import com.pppp.entities.Creators


internal data class DbCreators(override var items: List<DbItem> = emptyList()) : Creators