package com.thoughtworks.kotlin_basic.infrastructure.adapters

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.thoughtworks.kotlin_basic.domain.GetProductPort
import com.thoughtworks.kotlin_basic.domain.Product
import com.thoughtworks.kotlin_basic.domain.ProductId
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import java.math.BigDecimal

class ProductsRepositoryTest : FunSpec({
    val productsServer = WireMockServer(wireMockConfig().dynamicPort())
    register(WireMockListener(productsServer, ListenerMode.PER_SPEC))

    test("Should get the products") {
        productsServer.stubFor(
            get(urlEqualTo("/products"))
                .willReturn(
                    ok()
                        .withBodyFile("products_test_list.json")
                )
        )

        val port: GetProductPort = ProductRepository(productsServer.baseUrl())

        val actualProducts = port.getAllProducts()
        assertFalse(actualProducts.isEmpty())
        assertEquals(2, actualProducts.size)
        assertSoftly(findExpectedProduct(actualProducts, "ABC123")) {
            it.productId shouldBe ProductId("ABC123")
            it.name shouldBe "Electronic Watch"
            it.type shouldBe "NORMAL"
            it.basePrice shouldBe BigDecimal("299.99")
        }
        assertSoftly(findExpectedProduct(actualProducts, "DEF456")) {
            it.productId shouldBe ProductId("DEF456")
            it.name shouldBe "Sports Shoes"
            it.type shouldBe "NORMAL"
            it.basePrice shouldBe BigDecimal("499.6")
        }
    }
})

private fun findExpectedProduct(actualProducts: List<Product>, sku: String) =
    actualProducts.filter { it.productId.equals(ProductId(sku)) }.first()