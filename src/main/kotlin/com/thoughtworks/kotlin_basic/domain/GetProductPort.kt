package com.thoughtworks.kotlin_basic.domain

interface GetProductPort {

    fun getAllProducts(): List<Product>
}