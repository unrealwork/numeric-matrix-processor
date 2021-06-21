// You can experiment here, it won’t be checked

fun main() {
    val a = readLine()!!.toInt()
    val b = readLine()!!.toInt()
    val c = readLine()!!.toInt()
    println(isRightEquation(a, b, c))
}

fun isRightEquation(a: Int, b: Int, c: Int) = a*b == c
