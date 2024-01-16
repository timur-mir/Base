package com.example.roomdao30

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.math.BigDecimal
import java.time.Instant
import java.util.*

class TypeConvertersForBase {

@TypeConverter
fun fromDate(date: Date?): Long? {
    return  date?.time
}
    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }
@TypeConverter
   fun fromInstant(instant: Instant?):Long?{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            instant?.toEpochMilli()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
}

@TypeConverter
    fun toInstant(millisSinceEpoch: Long?):Instant? {
        return    millisSinceEpoch?.let{ if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Instant.ofEpochMilli(it)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        }
    }
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): String {
        return value.toString()
    }

    @TypeConverter
    fun stringToBigDecimal(value: String): BigDecimal {
        return BigDecimal(value)
    }
}