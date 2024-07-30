package com.thoughtworks.kotlin_basic.infrastructure.adapters

import com.thoughtworks.kotlin_basic.domain.GetProductStockLevelPort
import com.thoughtworks.kotlin_basic.domain.ProductId
import com.thoughtworks.kotlin_basic.domain.ProductStock
import com.thoughtworks.kotlin_basic.domain.WarehouseLocationId
import com.thoughtworks.kotlin_basic.infrastructure.common.Mapper
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ProductInventoryRepository(apiBaseUrl: String) : GetProductStockLevelPort {
    private val retrofit = Retrofit.Builder()
        .baseUrl(apiBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val inventoryService: InventoryService = retrofit.create(InventoryService::class.java)
    private val mapper: ProductInventoryMapper = ProductInventoryMapper()

    override fun getAllLocationStockLevels(): List<ProductStock> {
        return mapper.mapTo(inventoryService.getAllInventory().execute().body() ?: emptyList())
    }
}

interface InventoryService {
    @GET("/inventories")
    fun getAllInventory(): Call<List<ProductInventoryDTO>>
}

data class ProductInventoryDTO(
    val id: Int,
    val SKU: String,
    val zone: String,
    val quantity: Int,
)

class ProductInventoryMapper : Mapper<ProductInventoryDTO, ProductStock> {
    override fun mapTo(dto: List<ProductInventoryDTO>): List<ProductStock> {
        return dto.map { mapTo(it) }
    }

    override fun mapTo(dto: ProductInventoryDTO): ProductStock {
        return ProductStock(ProductId(dto.SKU), dto.quantity, WarehouseLocationId(dto.zone))
    }
}