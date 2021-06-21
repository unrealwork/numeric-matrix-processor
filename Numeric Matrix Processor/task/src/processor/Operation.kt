package processor

import java.lang.IllegalStateException

enum class Operation(
    override val choice: Int, override val title: String,
    override val children: Array<out MenuEntry> = emptyArray()
) : MenuEntry {
    ADD(1, "Add matrices"),
    SCALAR(2, "Multiply matrix by a constant"),
    TIMES(3, "Multiply matrices"),
    TRANSPOSE(4, "Transpose matrix", TransposeOperation.values()),
    DETERMINANT(5, "Calculate a determinant"),
    INVERSE(6, "Inverse matrix"),
    EXIT(0, "Exit");

    companion object {
        fun byChoice(choice: Int) = values()
            .find { it.choice == choice } ?: throw IllegalStateException("Unsupported choice")
    }
}