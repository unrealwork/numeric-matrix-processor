fun main() {
    val rooms = readLine()!!.toInt()
    val price = readLine()!!.toInt()
    val house = House.from(rooms, price)
    println(house.price.toInt())
}

open class House(open val rooms: Int, open var price: Double) {

    companion object {
        fun from(rooms: Int, p: Int): House {
            val price: Double = if (p > 1_000_000) 1_000_000.0 else if (p < 0) 0.0 else p.toDouble()
            return when {
                rooms == 1 -> Cabin(price)
                rooms in 2..3 -> Bungalow(rooms, price * 1.2)
                rooms == 4 -> Cottage(price * 1.25)
                rooms in 5..7 -> Mansion(rooms, price * 1.4)
                rooms > 8 -> Palace(rooms, price * 1.6)
                else -> Cabin(price)
            }
        }
    }

    private class Cabin(override var price: Double) : House(1, price)
    private class Bungalow(override val rooms: Int, override var price: Double) : House(rooms, price)
    private class Cottage(override var price: Double) : House(4, price)
    private class Mansion(override val rooms: Int, override var price: Double) : House(rooms, price) {
    }

    private class Palace(override val rooms: Int, override var price: Double) : House(rooms, price * 1.6)
}

