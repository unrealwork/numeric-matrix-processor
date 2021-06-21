fun solution(numbers: List<Int>) {
    // put your code here
    numbers
        .filter { it % 2 == 0}
        .forEach{ print("$it ") }
}
