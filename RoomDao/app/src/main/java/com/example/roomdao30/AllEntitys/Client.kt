package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.ClientsContract
import com.example.roomdao30.AllContracts.ClientsContract.TABLE_NAME
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = TABLE_NAME,
indices = [
    Index(ClientsContract.Columns.LOGIN, unique = true)]  )
//    //,
////Index(ClientsContract.Columns.ID)
//])
//)
data class  Client(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ClientsContract.Columns.ID)
    val id: Int,
    @ColumnInfo(name = ClientsContract.Columns.FIRST_NAME)
    val first_name: String,
    @ColumnInfo(name = ClientsContract.Columns.LAST_NAME)
    val last_name: String,
    @ColumnInfo(name = ClientsContract.Columns.EMAIL)
    val email: String,
    @ColumnInfo(name = ClientsContract.Columns.LOGIN)
    val login: String,
    @ColumnInfo(name = ClientsContract.Columns.PASSWORD)
    val password: String,
    @ColumnInfo(name = ClientsContract.Columns.AVATAR)
    val avatar: String?
)

