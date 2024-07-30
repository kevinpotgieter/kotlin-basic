package com.thoughtworks.kotlin_basic.application

import com.thoughtworks.kotlin_basic.domain.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.math.BigDecimal

class GetProductsAndPricesQueryHandlerTest : FunSpec({
    val productPort: GetProductPort = object : GetProductPort {
        override fun getAllProducts(): List<Product> {
            return runBlocking {
                delay(3500)
                println("Product information retrieved...")
                listOf(Product(ProductId("ABC123"), "Watch", BigDecimal.TEN, "NORMAL"))
            }
        }
    }
    val stockPort: GetProductStockLevelPort = object : GetProductStockLevelPort {
        override fun getAllLocationStockLevels(): List<ProductStock> {
            return runBlocking {
                delay(500)
                println("Inventory information retrieved...")
                listOf(ProductStock(ProductId("ABC123"), 12, WarehouseLocationId("EU_CENTRAL")))
            }
        }
    }
    val handler = GetProductsAndPricesQueryHandler(productPort, stockPort)


    test("Should return Products and Prices") {
        val actualResults = handler.getAllProductsWithAdjustedPrices()
        actualResults.size shouldBe 1
    }
})