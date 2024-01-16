package com.example.roomdao30.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.roomdao30.AllDaos.*
import com.example.roomdao30.AllEntitys.*
import com.example.roomdao30.TypeConvertersForBase

@Database(
    entities = [Client::class, Rating::class, Author::class,
        Product::class, BookShop::class,
        OfficialCar::class, Employee::class, Sale::class, Provider::class,
        Supply::class, Prices::class, Purchases::class,
        PurchasesPrices::class, PurchaseStatuse::class,PhoneNumber::class],
    version = ShopDataBase.DB_VERSION
)
@TypeConverters(TypeConvertersForBase::class)
abstract class ShopDataBase : RoomDatabase() {
    abstract fun clientDao(): ClientDao
    abstract fun ratingDao(): RatingDao
    abstract fun authorDao(): AuthorDao
    abstract fun productDao(): ProductDao
    abstract fun shopDao(): ShopDao
    abstract fun carDao(): OfficialCarDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun saleDao(): SaleDao
    abstract fun providerDao(): ProviderDao
    abstract fun supplyDao(): SupplyDao
    abstract fun pricesDao(): PricesDao
    abstract fun purchasesPricesDao(): PurchasesPricesDao
    abstract fun purchasesDao(): PurchasesAlt
    abstract fun purchaseStatuseDao(): PurchasesStatusDao
    abstract fun phoneNumberDao(): PhoneDao


    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "Books"
    }
}