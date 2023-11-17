package go.model

const val POSSIBLE_COL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
typealias Group = List<Position>
@JvmInline
value class Position private constructor(val idx: String){

    val line get() = idx.dropLast(1).toInt()
    val col get() = idx.last() - ('A'- 1)

    companion object {
        val values = List(BOARD_CELLS){
            val pos = "${BOARD_SIZE.value - (it/(BOARD_SIZE.value))}${POSSIBLE_COL[it%(BOARD_SIZE.value)]}"
            Position(pos)
        }
        operator fun invoke(idx: String): Position{
            val rightIdx =
            if(idx.last().uppercaseChar() !in POSSIBLE_COL){
                idx.switch()
            }
            else idx
            require(POSSIBLE_COL.take(BOARD_SIZE.value).contains(rightIdx.last().uppercase())){"Invalid column."}
            require(rightIdx.dropLast(1).toInt() in 1..BOARD_SIZE.value){"Invalid line."}
            val indice = (rightIdx.dropLast(1).toInt() - BOARD_SIZE.value)*-BOARD_SIZE.value + (rightIdx.uppercase().last() - 'A')
            return values[indice]
        }
        fun getIdx(position: Position) = values.indexOf(position)
    }
}
fun String.switch(): String = "${last()}${first()}"
fun String.toPosition() = Position(this)
fun Position.getAdj(): List<Position?>{
    val final = mutableListOf<Position?>()
    if (col - 1 == 0)  final.add(null) else final.add(Position.values[Position.getIdx(this) - 1])
    if (col + 1 > BOARD_SIZE.value) final.add(null) else final.add(Position.values[Position.getIdx(this) + 1])
    if (line + 1 > BOARD_SIZE.value) final.add(null) else final.add(Position.values[Position.getIdx(this) - BOARD_SIZE.value])
    if (line - 1 == 0) final.add(null) else final.add(Position.values[Position.getIdx(this) + BOARD_SIZE.value])
    return final
}