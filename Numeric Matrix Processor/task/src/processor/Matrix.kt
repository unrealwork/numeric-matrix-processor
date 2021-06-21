package processor

import java.util.*

interface Matrix<T : Number?> {
    companion object {

        private fun <T : Number?> readCommon(
            it: Scanner,
            reader: (sc: Scanner) -> T,
            matrixCreator: () -> Matrix<T>
        ): Matrix<T> {
            val matrix = matrixCreator()
            for (i in 0 until matrix.rows) {
                for (j in 0 until matrix.columns) {
                    matrix[i][j] = reader(it)
                }
            }
            return matrix
        }

        fun readDoubleMatrix(it: Scanner, rows: Int, columns: Int): Matrix<Double> =
            readCommon(it, { sc -> sc.nextDouble() }, { DoubleMatrix(rows, columns) })

    }

    val rows: Int
    val columns: Int
    fun data(): Array<Array<T>>

    fun content(): String = data().joinToString(System.lineSeparator())
    { row -> row.joinToString(" ") { it.toString() } }

    operator fun get(index: Int): Array<T> = data()[index]
    operator fun <S : Matrix<T>> plus(matrix: S): Matrix<T>
    operator fun times(scalar: T): Matrix<T>
    operator fun times(matrix: Matrix<T>): Matrix<T>
    fun transpose(type: TranspositionType = TranspositionType.MAIN_DIAG): Matrix<T>
    fun det(): T
    fun inverse(): Matrix<T>

    fun column(j: Int): Array<T>
}