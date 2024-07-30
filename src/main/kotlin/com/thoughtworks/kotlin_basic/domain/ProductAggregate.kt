package com.thoughtworks.kotlin_basic.domain

import java.math.BigDecimal

class ProductAggregate(private val product: Product, private val productStock: List<ProductStock>) {

    val productId: ProductId
        get() = product.productId


    val name: String
        get() = product.name

    val price: BigDecimal
        get() = product.calculatePrice(this.quantity)

    val quantity: Int
        get() = getStockLevelFor(productId)

    private fun getStockLevelFor(productId: ProductId): Int {
        return productStock.filter { it.productId == productId }.sumOf { it.quantity }
    }
}
