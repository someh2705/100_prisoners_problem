import type.BoxContent
import type.BoxNumber
import type.PrisonerNumber

class Game {
    private val prisoners: Array<Prisoner>
    private val room: RoomInfo

    init {
        val contents = Array(PrisonConfig.PRISONER_COUNT) { it + 1 }
        contents.shuffle()

        room = RoomInfo(contents.mapIndexed { index, content ->
            Box(BoxNumber(index + 1), BoxContent(content))
        }.toTypedArray())

        prisoners = Array(PrisonConfig.PRISONER_COUNT) {
            Prisoner(PrisonerNumber(it + 1), room)
        }
    }

    fun play() = prisoners.map { prisoner -> play(prisoner) }.all { it.isSuccess }

    private tailrec fun play(prisoner: Prisoner): Prisoner = when {
        prisoner.finish() -> prisoner
        else -> play(prisoner.open(prisoner.choose()))
    }
}
