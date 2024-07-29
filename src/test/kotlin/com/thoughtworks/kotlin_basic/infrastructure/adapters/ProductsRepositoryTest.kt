package com.thoughtworks.kotlin_basic.infrastructure.adapters

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.thoughtworks.kotlin_basic.domain.GetProductsPort
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import org.junit.jupiter.api.Assertions.assertFalse

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

        val port: GetProductsPort = ProductsRepository(productsServer.baseUrl())

        assertFalse(port.getAllProducts().isEmpty());
    }
})