package com.example.roomdao30

import android.app.Application
import com.example.roomdao30.DB.Database

class ShopDataBaseApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Database.init(this)
    }

}