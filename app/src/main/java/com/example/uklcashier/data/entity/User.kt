package com.example.uklcashier.data.entity

import android.provider.ContactsContract.Contacts.Photo
import android.widget.Spinner
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (

    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "nama user") var namaUser: String?,
    @ColumnInfo(name = "email") var email : String?,
    @ColumnInfo(name = "password") var password : String?,
    @ColumnInfo(name = "phone") var phone : String?,
)



