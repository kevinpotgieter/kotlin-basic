package com.thoughtworks.kotlin_basic.infrastructure.adapters

import com.thoughtworks.kotlin_basic.domain.GetProductsPort
import com.thoughtworks.kotlin_basic.domain.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class ProductsRepository(apiBaseUrl: String) : GetProductsPort {

    private val retrofit = Retrofit.Builder()
        .baseUrl(apiBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productService: ProductService = retrofit.create(ProductService::class.java)
    private val mapper: ProductMapper = ProductMapper()

    override fun getAllProducts(): List<Product> {
        return mapper.mapToProductList(productService.getAllProducts().execute().body() ?: emptyList())
    }
}

interface ProductService {
    @GET("/products")
    fun getAllProducts(): Call<List<ProductDTO>>
}

data class ProductDTO(
    val id: Int,
    val SKU: String,
    val name: String,
    val price: Double,
    val type: String,
    val image: String
)

class ProductMapper{
    fun mapToProductList(dto: List<ProductDTO>): List<Product> {
        return dto.map { mapToProduct(it) }
    }

    private fun mapToProduct(dto: ProductDTO): Product {
        return Product(dto.name, dto.SKU, dto.price, dto.type)
    }
}

