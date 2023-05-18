package com.example.uklcashier.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Meja(
    @PrimaryKey(autoGenerate = true)var meid: Int? = null,
    @ColumnInfo(name = "no meja") var noMeja: String?
)
