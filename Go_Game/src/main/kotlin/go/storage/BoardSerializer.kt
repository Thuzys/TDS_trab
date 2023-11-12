package go.storage

import go.model.*

object BoardSerializer:Seriealizer<Board> {
    override fun serialize(data: Board): String =
        when(data){
            is BoardRun -> "run ${data.turn} ${data.pass} ${data.removed}"
            is BoardWin -> "win ${data.winner}"
            is BoardDraw -> "draw"
        } + " | " +
                data.boardCells.entries.joinToString(" "){
                    (pos, player) -> "${pos.idx}:$player"
                }

    override fun deserialize(text: String): Board {
        val splitedText = text.split(" | ")
        val left = splitedText.first()
        val right = splitedText.last()
        val moves = if (right.isBlank()) emptyMap()
        else right.split(" ").map { it.split(":") }
            .associate { (idx, player) ->
                Position(idx) to Player.valueOf(player)
            }
        val (type, player, pass, removed) = left.split(" ")
        return when(type) {
            "run" -> BoardRun(moves, Player.valueOf(player), pass.toBooleanStrict(), Player.valueOf(removed))
            "win" -> BoardWin(moves, Player.valueOf(player))
            "draw" -> BoardDraw(moves)
            else -> error("Invalid board type: $type")
        }
    }

}