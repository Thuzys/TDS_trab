package go.model

const val BOARD_SIZE = 9
const val BOARD_CELLS = BOARD_SIZE * BOARD_SIZE


typealias BoardCells = Map<Position, Player>
sealed class Board(val boardCells: BoardCells)
class BoardRun(boardCells: BoardCells, val turn: Player): Board(boardCells)
class BoardWin(boardCells: BoardCells, val winner: Player): Board(boardCells)
class BoardDraw(boardCells: BoardCells): Board(boardCells)
fun Board(start: Player = Player.#): Board =  BoardRun(emptyMap(), start)
fun Board.play(playPositionIdx: Position): Board = when(this){
    is BoardRun -> {
        require(boardCells[playPositionIdx] == null){"Position ${playPositionIdx}'s used."}
        val boardCellsAfterPlay = boardCells + (playPositionIdx to turn)
        when {
            isWinner() -> BoardWin(boardCellsAfterPlay, turn)
            boardCellsAfterPlay.size == BOARD_CELLS -> BoardDraw(boardCellsAfterPlay)
            else -> BoardRun(boardCellsAfterPlay, turn.other)
        }
    }
    is BoardDraw, is BoardWin -> error("Game Over.")
}
private fun isWinner():Boolean{
    TODO()
}