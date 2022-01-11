package com.mokapos.americano.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg product: Product): List<Long>

    @Query("DELETE from product")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM product")
    fun count(): Int

    @Query("SELECT * FROM product")
    fun getAll(): List<Product>
}