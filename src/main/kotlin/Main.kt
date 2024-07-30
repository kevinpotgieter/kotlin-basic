import com.thoughtworks.kotlin_basic.application.GetProductsAndPricesQueryHandler
import com.thoughtworks.kotlin_basic.domain.GetProductPort
import com.thoughtworks.kotlin_basic.domain.GetProductStockLevelPort
import com.thoughtworks.kotlin_basic.infrastructure.adapters.ProductInventoryRepository
import com.thoughtworks.kotlin_basic.infrastructure.adapters.ProductRepository
import com.thoughtworks.kotlin_basic.util.PrintUtil

fun main(args: Array<String>) {

    val printUtil = PrintUtil()
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.

    val productPort: GetProductPort = ProductRepository("http://localhost:3000/")
    val stockPort: GetProductStockLevelPort = ProductInventoryRepository("http://localhost:3000/")
    val queryHandler = GetProductsAndPricesQueryHandler(productPort, stockPort)
    val results = queryHandler.getAllProductsWithAdjustedPrices()
    val headers = listOf("SKU", "Name", "Price", "Quantity")
    val rows =
        results.map { listOf(it.productId.sku, it.name, it.price.toPlainString(), it.quantity.toString()) }


    printUtil.printTable(headers, rows)
}