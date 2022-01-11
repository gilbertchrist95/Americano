package com.mokapos.americano.data

import javax.inject.Inject

interface ProductRepository {
    suspend fun countProduct(): Int

    suspend fun insertAll(product: Array<Product>): List<Long>

    suspend fun getAll(): List<Product>
}

class ProductRepositoryImpl @Inject constructor(private val productDao: ProductDao) :
    ProductRepository {
    override suspend fun countProduct(): Int {
        return productDao.count()
    }

    override suspend fun insertAll(products: Array<Product>): List<Long> {
        return productDao.insertAll(*products)
    }

    override suspend fun getAll(): List<Product> {
        return productDao.getAll()
    }
}