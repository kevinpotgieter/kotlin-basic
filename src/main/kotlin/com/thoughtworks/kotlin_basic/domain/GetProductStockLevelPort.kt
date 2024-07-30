package com.thoughtworks.kotlin_basic.domain

interface GetProductStockLevelPort {

    fun getAllLocationStockLevels(): List<ProductStock>;
}