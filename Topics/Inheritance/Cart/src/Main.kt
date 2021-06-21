fun main() {
    val productType = readLine()!!
    val price = readLine()!!.toInt()
    val product = Product.withType(productType)(price)
    println(totalPrice(product))
}

fun totalPrice(product: Product): Int = product.price + (product.price * (product.tax / 100.0)).toInt()

abstract class Product(open val price: Int) {
    companion object {
        fun withType(productType: String): (Int) -> Product = when (productType) {
            "headphones" -> ::HeadPhones
            "smartphone" -> ::Smartphone
            "tv" -> ::TV
            "laptop" -> ::Laptop
            else -> error("Invalid type")
        }
    }

    abstract val tax: Int

    private class HeadPhones(override val price: Int, override val tax: Int = 11) : Product(price)
    private class Smartphone(override val price: Int, override val tax: Int = 15) : Product(price)
    private class TV(override val price: Int, override val tax: Int = 17) : Product(price)
    private class Laptop(override val price: Int, override val tax: Int = 19) : Product(price)
}

