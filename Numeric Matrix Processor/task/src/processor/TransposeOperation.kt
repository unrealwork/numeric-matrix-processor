package processor

import java.lang.IllegalStateException

enum class TransposeOperation(
    override val choice: Int,
    override val title: String,
    val type: TranspositionType
) : MenuEntry {
    MAIN_DIAG(1, "Main diagonal", TranspositionType.MAIN_DIAG),
    SIDE_DIAG(2, "Main diagonal", TranspositionType.SIDE_DIAG),
    VERTICAL_LINE(3, "Vertical line", TranspositionType.VERTICAL_LINE),
    HORIZONTAL_LINE(4, "Horizontal line", TranspositionType.HORIZONTAL_LINE);


    override val children: Array<out MenuEntry> = emptyArray()

    companion object {
        fun byChoice(choice: Int) = values()
            .find { it.choice == choice } ?: throw IllegalStateException("Unsupported choice")
    }
}
