import type.PrisonerNumber

data class Prisoner(
    private val number: PrisonerNumber,
    private val room: RoomInfo,
    private val history: List<Box> = emptyList(),
    val isSuccess: Boolean = false,
) {
    fun choose(): Box {
        val lastBox = history.lastOrNull() ?: chooseFirst()
        val nextNumber = lastBox.content
        return room.value.first { it.number.value == nextNumber.value }
    }

    private fun chooseFirst(): Box = room.value.first { it.number.value == number.value }

    fun open(box: Box): Prisoner = copy(
        number = number,
        room = room,
        history = history + box,
        isSuccess = box.isContent(number.value)
    )

    fun finish(): Boolean = isSuccess || history.size == 50
}