package go.model

const val POSSIBLE_COL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

@JvmInline
value class Position private constructor(val idx: String){
    val line get() = idx.dropLast(1).toInt()

    val col get() = idx.last() - ('A'- 1)

    companion object {
        val values = List(BOARD_CELLS){
            val pos = "${BOARD_SIZE - (it/(BOARD_SIZE))}${POSSIBLE_COL[it%(BOARD_SIZE)]}"
            Position(pos)
        }
        operator fun invoke(idx: String): Position{
            require(POSSIBLE_COL.take(BOARD_SIZE).contains(idx.last().uppercase())){"Invalid column."}
            require(idx.dropLast(1).toInt() in 1..BOARD_SIZE){"Invalid line."}
            val indice = (idx.dropLast(1).toInt() - BOARD_SIZE)*-BOARD_SIZE + (idx.uppercase().last() - 'A')
            return values[indice]
        }
    }
}
fun String.toPosition() = Position(this) // invoke(this)
