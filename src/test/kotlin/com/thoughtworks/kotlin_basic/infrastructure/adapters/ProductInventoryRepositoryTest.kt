package com.thoughtworks.kotlin_basic.infrastructure.adapters

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.thoughtworks.kotlin_basic.domain.GetProductStockLevelPort
import com.thoughtworks.kotlin_basic.domain.ProductId
import com.thoughtworks.kotlin_basic.domain.ProductStock
import com.thoughtworks.kotlin_basic.domain.WarehouseLocationId
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse

class ProductInventoryRepositoryTest : FunSpec({
    val inventoryServer = WireMockServer(wireMockConfig().dynamicPort())
    register(WireMockListener(inventoryServer, ListenerMode.PER_SPEC))

    test("Should get the product inventories") {
        inventoryServer.stubFor(
            get(urlEqualTo("/inventories"))
                .willReturn(
                    ok()
                        .withBodyFile("product_inventory_test_list.json")
                )
        )

        val port: GetProductStockLevelPort = ProductInventoryRepository(inventoryServer.baseUrl())

        val actualStockLevels = port.getAllLocationStockLevels()
        assertFalse(actualStockLevels.isEmpty())
        assertEquals(3, actualStockLevels.size)

        assertSoftly(findExpectedProductStockForWarehouse(actualStockLevels, "ABC123", "CN_NORTH")) {
            it.productId shouldBe ProductId("ABC123")
            it.warehouseLocationId shouldBe WarehouseLocationId("CN_NORTH")
            it.quantity shouldBe 120
        }

        assertSoftly(findExpectedProductStockForWarehouse(actualStockLevels, "ABC123", "US_WEST")) {
            it.productId shouldBe ProductId("ABC123")
            it.warehouseLocationId shouldBe WarehouseLocationId("US_WEST")
            it.quantity shouldBe 80
        }
        assertSoftly(findExpectedProductStockForWarehouse(actualStockLevels, "DEF456", "EU_CENTRAL")) {
            it.productId shouldBe ProductId("DEF456")
            it.warehouseLocationId shouldBe WarehouseLocationId("EU_CENTRAL")
            it.quantity shouldBe 200
        }
    }
})

private fun findExpectedProductStockForWarehouse(
    actualProducts: List<ProductStock>,
    sku: String,
    warehouseLocation: String
) =
    actualProducts.first {
        it.productId == ProductId(sku) && it.warehouseLocationId == WarehouseLocationId(
            warehouseLocation
        )
    }
