package com.thoughtworks.kotlin_basic.application

import com.thoughtworks.kotlin_basic.domain.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class GetProductsAndPricesQueryHandler(
    private val getProducts: GetProductPort,
    private val productStock: GetProductStockLevelPort
) {

    fun getAllProductsWithAdjustedPrices(): List<ProductAggregate> {
        return runBlocking {
            println("At the beginning of the coroutine...")
            val deferredProducts = async { loadProducts() }
            val deferredInventories = async { loadProductInventories() }
            println("About to map the results(with appropriate wait() calls")
            deferredProducts.await().map { ProductAggregate(it, deferredInventories.await()) }
        }
    }

    // Concurrently executes both sections
    private suspend fun loadProducts(): List<Product> {
        println("Calling to get product information...")
        return this.getProducts.getAllProducts()
    }

    private suspend fun loadProductInventories(): List<ProductStock> {
        println("Calling to get inventory information...")
        return this.productStock.getAllLocationStockLevels()
    }
}