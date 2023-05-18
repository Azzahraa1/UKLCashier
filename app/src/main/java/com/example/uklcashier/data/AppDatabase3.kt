package com.example.uklcashier.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklcashier.adapter.MejaAdapter
import com.example.uklcashier.data.dao.MejaDao
import com.example.uklcashier.data.dao.MenuDao
import com.example.uklcashier.data.entity.Meja
import com.example.uklcashier.data.entity.Menu


@Database(entities = [Meja::class], version = 3)
abstract class AppDatabase3 : RoomDatabase(){
    abstract fun MejaDao(): MejaDao

    companion object{
        private var instance: AppDatabase3? = null

        fun getInstance(context: Context):AppDatabase3{
            if(instance == null){
                instance = Room.databaseBuilder(context,AppDatabase3::class.java, "app-database2")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}