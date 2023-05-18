package com.example.uklcashier.data.dao

import androidx.room.*
import com.example.uklcashier.data.entity.Meja


@Dao
interface MejaDao {


    @Query("SELECT * FROM meja")
    fun getAll():List<Meja>

    @Query("SELECT * FROM meja WHERE meid IN(:mejaIds)")
    fun loadAllByIds(mejaIds: IntArray):List<Meja>

    @Insert
    fun insertAll(vararg mejas: Meja)

    @Delete
    fun delete(meja: Meja)

    @Query("SELECT * FROM meja WHERE meid = :meid")
    fun get(meid: Int) : Meja

    @Update
    fun update(meja: Meja)

}