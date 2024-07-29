package com.thoughtworks.kotlin_basic.domain

interface GetProductsPort {

    fun getAllProducts(): List<Product>;
}