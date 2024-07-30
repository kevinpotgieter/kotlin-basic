package com.thoughtworks.kotlin_basic.domain

import java.math.BigDecimal

class ProductAggregate(private val product: Product, productStock: List<ProductStock>) {

    val productId: ProductId
        get() = product.productId


    val name: String
        get() = product.name

    val price: BigDecimal
        get() = calculatePrice()

    private fun calculatePrice(): BigDecimal {
        return product.basePrice
    }
}