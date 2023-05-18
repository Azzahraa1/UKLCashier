package com.example.uklcashier.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklcashier.data.dao.MenuDao
import com.example.uklcashier.data.entity.Menu


@Database(entities = [Menu::class], version = 2)
abstract class AppDatabase2 : RoomDatabase(){
    abstract fun MenuDao(): MenuDao

    companion object{
        private var instance: AppDatabase2? = null

        fun getInstance(context: Context):AppDatabase2{
            if(instance == null){
                instance = Room.databaseBuilder(context,AppDatabase2::class.java, "app-database2")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}