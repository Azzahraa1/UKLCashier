package com.example.uklcashier.data.dao

import androidx.room.*
import com.example.uklcashier.data.entity.Menu

@Dao
interface MenuDao {


    @Query("SELECT * FROM menu")
    fun getAll():List<Menu>

    @Query("SELECT * FROM menu WHERE mid IN(:menuIds)")
    fun loadAllByIds(menuIds: IntArray):List<Menu>

    @Insert
    fun insertAll(vararg menus: Menu)

    @Delete
    fun delete(menu: Menu)

    @Query("SELECT * FROM menu WHERE mid = :mid")
    fun get(mid: Int) : Menu

    @Update
    fun update(menu: Menu)

    @Query("SELECT * FROM menu WHERE jenis =:jenisMenu")
    fun getMenuFilterJenis(jenisMenu: String): List<Menu>
}