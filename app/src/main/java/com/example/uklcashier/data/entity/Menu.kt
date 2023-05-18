package com.example.uklcashier.data.entity

import android.widget.Spinner
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Menu (

    @PrimaryKey(autoGenerate = true)var mid:Int? = null,
    @ColumnInfo(name = "nama menu") var namaMenu: String?,
    @ColumnInfo(name = "deskripsi") var deskripsi : String?,
    @ColumnInfo(name = "harga") var harga: String?,
    @ColumnInfo(name = "jenis")  var jenis: String

    )