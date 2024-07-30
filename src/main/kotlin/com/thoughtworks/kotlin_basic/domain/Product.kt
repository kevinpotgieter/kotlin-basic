package com.thoughtworks.kotlin_basic.domain

import java.math.BigDecimal
import java.math.RoundingMode

class Product(val productId: ProductId, val name: String, val basePrice: BigDecimal, val type: String) {

    fun calculatePrice(stockLevel: Int): BigDecimal {
        return if (isHighDemand()) {
            when (stockLevel) {
                in 31..100 -> basePrice.multiply(BigDecimal("120.00")).divide(BigDecimal("100.00"))
                    .setScale(2, RoundingMode.CEILING)

                in 1..30 -> basePrice.multiply(BigDecimal("150.00")).divide(BigDecimal("100.00"))
                    .setScale(2, RoundingMode.CEILING)

                else -> basePrice
            }
        } else {
            basePrice
        }
    }

    private fun isHighDemand(): Boolean {
        return this.type == "HIGH_DEMAND"
    }

}
