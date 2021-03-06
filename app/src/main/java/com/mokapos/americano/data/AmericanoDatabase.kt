package com.mokapos.americano.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AmericanoDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

}