package com.example.roomdao30.DB

import android.content.Context
import androidx.room.Room
import com.example.roomdao30.migration_1_2

object Database {

 var INSTANCE: ShopDataBase?=null
private set

    fun init(context: Context) {
        if (INSTANCE == null) {
            synchronized(ShopDataBase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context, ShopDataBase::class.java,
                        ShopDataBase.DB_NAME
                    )
                        .addMigrations(migration_1_2)

//                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }

    }
}
//    lateinit var instance: ShopDataBase
//        private set
//
//    fun init(context: Context) {
//        instance = Room.databaseBuilder(
//            context, ShopDataBase::class.java,
//            ShopDataBase.DB_NAME
//        )
//            .build()
//    }
