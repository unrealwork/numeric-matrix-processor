fun main() {
    // put your code here
    var s = ""
    for (i in 1..5) {
        s += readLine()!!
        if (i < 5) {
            s += " "
        }
    }
    println(s)
}