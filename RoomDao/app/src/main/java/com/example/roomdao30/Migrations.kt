package com.example.roomdao30

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdao30.AllContracts.PhoneNumberContract

val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE ${PhoneNumberContract.TABLE_NAME} ADD COLUMN ${PhoneNumberContract.Columns.EMAIL} TEXT NOT NULL DEFAULT ''"
        )
    }
}