package processor

import java.util.*
import kotlin.system.exitProcess

fun main() {
    Scanner(System.`in`).use {
        while (true) {
            provideChoice(it)
        }
    }
}

private fun provideChoice(it: Scanner) {
    Operation.values()
        .forEach { op -> println("${op.choice}. ${op.title}") }
    print("Your choice: ")
    try {
        when (val operation = Operation.byChoice(it.nextInt())) {
            Operation.ADD -> {
                print("Enter size of first matrix: ")
                var rows = it.nextInt()
                var columns = it.nextInt()
                println("Enter first matrix:")
                val a = Matrix.readDoubleMatrix(it, rows, columns)
                print("Enter size of second matrix: ")
                rows = it.nextInt()
                columns = it.nextInt()
                println("Enter second matrix:")
                val b = Matrix.readDoubleMatrix(it, rows, columns)
                println("The result is:")
                println(a + b)
            }
            Operation.SCALAR -> {
                print("Enter size of matrix: ")
                val rows = it.nextInt()
                val columns = it.nextInt()
                println("Enter matrix:")
                val a = Matrix.readDoubleMatrix(it, rows, columns)
                print("Enter constant: ")
                val scalar = readLine()!!.toDouble()
                println("The result is:")
                println(a * scalar)
            }
            Operation.TIMES -> {
                print("Enter size of first matrix: ")
                var rows = it.nextInt()
                var columns = it.nextInt()
                println("Enter first matrix:")
                val a = Matrix.readDoubleMatrix(it, rows, columns)
                print("Enter size of second matrix: ")
                rows = it.nextInt()
                columns = it.nextInt()
                println("Enter second matrix:")
                val b = Matrix.readDoubleMatrix(it, rows, columns)
                println("The result is:")
                println(a * b)
            }
            Operation.TRANSPOSE -> {
                operation.children
                    .forEach { op -> println("${op.choice}. ${op.title}") }
                print("Your choice: ")
                val transposeOperation = TransposeOperation.byChoice(it.nextInt())
                print("Enter size of matrix: ")
                val rows = it.nextInt()
                val columns = it.nextInt()
                println("Enter matrix:")
                val a = Matrix.readDoubleMatrix(it, rows, columns)
                println("The result is:")
                println(a.transpose(transposeOperation.type))
            }
            Operation.DETERMINANT -> {
                print("Enter size of matrix: ")
                val rows = it.nextInt()
                val columns = it.nextInt()
                println("Enter matrix:")
                val a = Matrix.readDoubleMatrix(it, rows, columns)
                println("The result is:")
                println(a.det())
            }
            Operation.INVERSE -> {
                print("Enter size of matrix: ")
                val rows = it.nextInt()
                val columns = it.nextInt()
                println("Enter matrix:")
                val a = Matrix.readDoubleMatrix(it, rows, columns)
                println("The result is:")
                println(a.inverse())
            }
            Operation.EXIT -> exitProcess(0)
        }
    } catch (e: NotExistingInverseMatrixException) {
        println("This matrix doesn't have an inverse.")
    } catch (e: Exception) {
        println("The operation cannot be performed.")
    }
}
