package go.model

data class Game(
    val board: Board? = null,
    val captured: Map<Player?, Int> = (Player.entries + null).associateWith { 0 },
    val firstPlayer: Player = Player.X,
    val score: Map<Player?, Double> = (Player.entries + null).associateWith { 0.0 }
)

fun Game.play(pos: Position): Game{
    checkNotNull(board){"Game not started."}
    val newBoard = board.play(pos)
    return copy(
        board = newBoard,
        captured = captured.calcCaptured(newBoard, board)
    )
}

private fun Map<Player?, Int>.calcCaptured(new: Board, old: Board): Map<Player?, Int>{
    require(new is BoardRun)
    return if (new.boardCells.size <= old.boardCells.size){
        mapValues {
            if (it.key != new.removed)
                it.value + (old.boardCells.size - new.boardCells.size + 1)
            else
                it.value
        }
    }else
        this
}
fun Game.newBoard(): Game = Game(
    Board(start = firstPlayer),
    firstPlayer = firstPlayer.other
)
fun Game.pass(): Game {
    require(board is BoardRun){"Invalid Command."}
    return if (!board.pass){
        copy(board = board.pass())
    }else{
        captured.tellWinner(board.boardCells, firstPlayer.other)?.let {
            copy(board = BoardWin(board.boardCells, it.second), score = it.first)
        } ?:
        copy(board = BoardDraw(board.boardCells))
    }
}
private fun Map<Player?, Int>.tellWinner(board: BoardCells, firstPlayer: Player):Pair<Map<Player?, Double>, Player>?{
    val voidPoints = board.tellVoidCellsPoints()
    val sum = mapValues { it.value + (voidPoints[it.key] ?: 0) }.mapValues {
        if (it.key == firstPlayer) {
            val sub = it.value - BOARD_SIZE.sub
            if (sub >= 0) sub else 0.0
        }
        else
            it.value.toDouble()
    }
    val biggest = sum.map { it.key to it.value }.maxByOrNull { it.second }
    return if (sum.filter { it.value == biggest?.second }.size < 2) biggest?.first?.let { sum to it } else null
}