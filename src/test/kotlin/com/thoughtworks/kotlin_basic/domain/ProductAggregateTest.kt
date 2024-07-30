package com.thoughtworks.kotlin_basic.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import java.math.BigDecimal
import java.util.*

class ProductAggregateTest : FunSpec({

    test("If the product is normal, the actual price is equal to the original price") {
        val testProduct: Product = createTestProduct("NORMAL")
        val productStockLevels: List<ProductStock> = listOf(createTestProductStockLevel(testProduct.productId, 10))

        val productWithPrice = ProductAggregate(testProduct, productStockLevels)
        productWithPrice.price shouldBeEqual testProduct.basePrice
    }

    test("If the product is high-demand, and stock is greater than 100, the price is equal to the original price") {
        val testProduct: Product = createTestProduct("HIGH_DEMAND")
        val productStockLevels: List<ProductStock> = listOf(
            createTestProductStockLevel(testProduct.productId, 10),
            createTestProductStockLevel(testProduct.productId, 92),
            createTestProductStockLevel(ProductId(randomStringForTest()), 100)
        )

        val productWithPrice = ProductAggregate(testProduct, productStockLevels)
        productWithPrice.price shouldBeEqual testProduct.basePrice
    }

    test("If the product is high-demand, and stock is less than or equal to 100 and greater than 30, the price is 120% of the original price") {
        val testProduct: Product = createTestProduct("HIGH_DEMAND", BigDecimal("562.89"))
        val productStockLevels: List<ProductStock> = listOf(
            createTestProductStockLevel(testProduct.productId, 8),
            createTestProductStockLevel(testProduct.productId, 92),
            createTestProductStockLevel(ProductId(randomStringForTest()), 100)
        )

        val productWithPrice = ProductAggregate(testProduct, productStockLevels)
        productWithPrice.price shouldBeEqual BigDecimal("675.47")
    }

    test("If the product is high-demand, and stock is less than or equal to 30, the price is 150% of the original price") {
        val testProduct: Product = createTestProduct("HIGH_DEMAND", BigDecimal("562.89"))
        val productStockLevels: List<ProductStock> = listOf(
            createTestProductStockLevel(testProduct.productId, 8),
            createTestProductStockLevel(testProduct.productId, 22),
            createTestProductStockLevel(ProductId(randomStringForTest()), 100)
        )

        val productWithPrice = ProductAggregate(testProduct, productStockLevels)
        productWithPrice.price shouldBeEqual BigDecimal("844.34")
    }

})

private fun createTestProduct(productDemandType: String, basePrice: BigDecimal = BigDecimal("123.97")): Product {
    return Product(
        ProductId("123BBC"), "Nike Shoes", basePrice,
        productDemandType
    )
}

private fun createTestProductStockLevel(productId: ProductId, quantity: Int) =
    ProductStock(
        productId,
        quantity,
        WarehouseLocationId(randomStringForTest())
    )

// I'm being lazy to generate a random warehouse string.
private fun randomStringForTest() = UUID.randomUUID().toString().substring(0, 15)