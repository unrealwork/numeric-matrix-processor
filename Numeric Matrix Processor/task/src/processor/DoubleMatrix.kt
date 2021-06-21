package processor

import kotlin.math.sqrt


data class DoubleMatrix(override val rows: Int, override val columns: Int) : Matrix<Double> {
    private val data: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }

    override fun <S : Matrix<Double>> plus(matrix: S): Matrix<Double> {
        validateSecondOperand(matrix)
        val res = DoubleMatrix(rows, columns)
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                res[i][j] = data[i][j] + matrix[i][j]
            }
        }
        return res
    }

    override fun times(scalar: Double): Matrix<Double> {
        val res = DoubleMatrix(rows, columns)
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                res[i][j] = data[i][j] * scalar
            }
        }
        return res
    }

    override fun data(): Array<Array<Double>> = data

    private fun <T : Number> validateSecondOperand(matrix: Matrix<T>) {
        if (rows != matrix.rows || columns != matrix.columns) {
            throw NotMatchingDimensionsException()
        }
    }

    override fun times(matrix: Matrix<Double>): Matrix<Double> {
        if (columns != matrix.rows) {
            throw NotMatchingDimensionsException()
        }
        val res = DoubleMatrix(rows, matrix.columns)
        for (i in 0 until rows) {
            for (j in 0 until matrix.columns) {
                res[i][j] = this[i].zip(matrix.column(j))
                    .sumOf { p -> p.first * p.second }
            }
        }
        return res
    }

    override fun toString(): String = content()

    override fun column(j: Int): Array<Double> = data().map { row -> row[j] }
        .toTypedArray()

    override fun transpose(type: TranspositionType): Matrix<Double> = when (type) {
        TranspositionType.MAIN_DIAG -> {
            val res = copy(rows = columns, columns = rows)
            for (i in 0 until rows) {
                for (j in 0..i) {
                    val offset = i - j
                    res[i][j] = data[i - offset][i]
                    res[i - offset][i] = data[i][j]
                }
            }
            res
        }
        TranspositionType.SIDE_DIAG -> {
            val res = copy(rows = columns, columns = rows)
            for (i in 0 until rows) {
                for (j in 0 until columns - i) {
                    val offset = columns - i - j - 1
                    res[i][j] = data[i + offset][columns - i - 1]
                    res[i + offset][columns - i - 1] = data[i][j]
                }
            }
            res
        }
        TranspositionType.VERTICAL_LINE -> {
            val res = copy()
            val hasEvenColumns = columns % 2 == 0
            val middle = columns / 2
            val jRange = if (hasEvenColumns) (0 until middle) else (0..middle)
            for (i in 0 until rows) {
                for (j in jRange) {
                    val offset = if (hasEvenColumns) middle - j - 1 else middle - j
                    val mirrorJ = middle + offset
                    res[i][j] = data[i][mirrorJ]
                    res[i][mirrorJ] = data[i][j]
                }
            }
            res
        }
        TranspositionType.HORIZONTAL_LINE -> {
            val res = copy()
            val hasEvenRows = rows % 2 == 0
            val middle = rows / 2
            val iRange = if (hasEvenRows) (0 until middle) else (0..middle)
            for (i in iRange) {
                for (j in 0 until columns) {
                    val offset = if (hasEvenRows) middle - i - 1 else middle - i
                    val mirrorI = middle + offset
                    res[i][j] = data[mirrorI][j]
                    res[mirrorI][j] = data[i][j]
                }
            }
            res
        }
    }


    private fun minor(minorI: Int, minorJ: Int): DoubleMatrix {
        val res = DoubleMatrix(rows - 1, columns - 1)
        for (i in 0 until rows) {
            if (i == minorI) continue
            for (j in 0 until columns) {
                if (j == minorJ) continue
                val resI = if (i > minorI) i - 1 else i
                val resJ = if (j > minorJ) j - 1 else j
                res[resI][resJ] = data[i][j]
            }
        }
        return res
    }

    private fun cofactor(i: Int, j: Int): Double = (if ((i + j) % 2 == 0) 1 else -1) * minor(i, j).det()
    private fun cofactor(): DoubleMatrix {
        val res = DoubleMatrix(rows, columns)
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                res[i][j] = cofactor(i, j)
            }
        }
        return res
    }

    override fun det(): Double {
        if (rows != columns) {
            throw NotMatchingDimensionsException("Determinant can be calculated only for square matrix")
        }
        if (rows == 2) {
            return data[0][0] * data[1][1] - data[0][1] * data[1][0]
        }
        return (0 until columns).sumOf { data[0][it] * cofactor(0, it) }
    }

    override fun inverse(): Matrix<Double> {
        if (rows != columns) {
            throw NotMatchingDimensionsException("Inverse matrix can be calculated only for square matrix")
        }
        val d = det()
        if (d == 0.0) {
            throw NotExistingInverseMatrixException("The matrix doesn't have an inverse.")
        }
        return cofactor().transpose() * (1 / d)
    }
}