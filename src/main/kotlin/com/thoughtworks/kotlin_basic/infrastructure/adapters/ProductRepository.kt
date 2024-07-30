package com.thoughtworks.kotlin_basic.infrastructure.adapters

import com.thoughtworks.kotlin_basic.domain.GetProductPort
import com.thoughtworks.kotlin_basic.domain.Product
import com.thoughtworks.kotlin_basic.domain.ProductId
import com.thoughtworks.kotlin_basic.infrastructure.common.Mapper
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.math.BigDecimal


class ProductRepository(apiBaseUrl: String) : GetProductPort {

    private val retrofit = Retrofit.Builder()
        .baseUrl(apiBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productService: ProductService = retrofit.create(ProductService::class.java)
    private val mapper: ProductMapper = ProductMapper()

    override fun getAllProducts(): List<Product> {
        return mapper.mapTo(productService.getAllProducts().execute().body() ?: emptyList())
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
    val price: BigDecimal,
    val type: String,
    val image: String
)

class ProductMapper : Mapper<ProductDTO, Product> {
    override fun mapTo(dto: List<ProductDTO>): List<Product> {
        return dto.map { mapTo(it) }
    }

    override fun mapTo(dto: ProductDTO): Product {
        return Product(ProductId(dto.SKU), dto.name, dto.price, dto.type)
    }
}

