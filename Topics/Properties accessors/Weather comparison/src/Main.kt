class City(val name: String) {
    var degrees: Int = 0
}

fun isValid(temp: Int): Int? {
    if (temp >= -92 && temp <= 57) {
        return temp;
    }
    return null
}

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()
    val firstCity = City("Dubai")
    firstCity.degrees = isValid(first) ?: 30
    val secondCity = City("Moscow")
    secondCity.degrees = isValid(second) ?: 5
    val thirdCity = City("Hanoi")
    thirdCity.degrees = isValid(third) ?: 20

    //implement comparing here
    val cities = arrayOf(firstCity, secondCity, thirdCity)
    val min = cities.minOf { city: City -> city.degrees }
    val minTempCities = cities.filter { c -> c.degrees == min }.toTypedArray()
    print(if (minTempCities.size == 1) minTempCities.first().name else "neither")
}