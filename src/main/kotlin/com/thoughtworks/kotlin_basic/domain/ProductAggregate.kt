package com.thoughtworks.kotlin_basic.domain

import java.math.BigDecimal

class ProductAggregate(private val product: Product, private val productStock: List<ProductStock>) {

    val productId: ProductId
        get() = product.productId


    val name: String
        get() = product.name

    val price: BigDecimal
        get() = calculatePrice()

    private fun calculatePrice(): BigDecimal {
        val productStockLevel = getStockLevelFor(product.productId)
        return product.calculatePrice(productStockLevel)
    }

    private fun getStockLevelFor(productId: ProductId): Int {
        return productStock.filter { it.productId == productId }.sumOf { it.quantity }
    }
}