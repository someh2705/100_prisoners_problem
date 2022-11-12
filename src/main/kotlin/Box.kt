import type.BoxContent
import type.BoxNumber

data class Box(val number: BoxNumber, val content: BoxContent) {
    fun isContent(value: Int): Boolean {
        return content.value == value
    }
}
