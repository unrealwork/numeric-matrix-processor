package processor

interface MenuEntry {
    val choice: Int
    val title: String
    val children: Array<out MenuEntry>
}
