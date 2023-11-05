package go.model

const val BOARD_SIZE = 13
const val BOARD_CELLS = BOARD_SIZE * BOARD_SIZE

typealias BoardCells = Map<Position, Player>
sealed class Board(val boardCells: BoardCells)
class BoardRun(boardCells: BoardCells, val turn: Player, val pass: Boolean = false): Board(boardCells)
class BoardWin(boardCells: BoardCells, val winner: Player): Board(boardCells)
//class BoardDraw(boardCells: BoardCells): Board(boardCells)
fun Board(start: Player = Player.X): Board =  BoardRun(emptyMap(), start)
fun Board.play(playPositionIdx: Position): Board = when(this){
    is BoardRun -> {
        require(boardCells[playPositionIdx] == null){"Position ${playPositionIdx.idx} is used."}
        val boardCellsAfterPlay = boardCells + (playPositionIdx to turn)
        BoardRun(boardCellsAfterPlay, turn.other)
    }
    is BoardWin -> error("Game Over.")
}

fun Board?.pass() = when(this){
    is BoardRun -> {
        if (!pass)
            BoardRun(boardCells, turn.other, true)
        else
            BoardWin(boardCells, boardCells.tellWinner())
    }
    else -> error("Invalid action.")
}



//private fun isWinner():Boolean{
//    TODO()
//}