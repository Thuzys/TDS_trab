package go.view

import go.model.*

private const val separator = "."

fun Game.show(){
    board?.show()
    when(board){
        is BoardRun -> println("Turn: ${board.turn.simbol}(${board.turn.color})").also { captured.show("Captured") }
        is BoardDraw -> println("Draw")
        is BoardWin -> println("Winner: ${board.winner.simbol}(${board.winner.color})").also { score.show("Score") }
        else -> return
    }
}
private fun <K> Map<Player?, K>.show(msg: String){
    print("$msg: ")
    forEach { (player, points) -> player?.let { print("| ${it.simbol}(${it.color}) -> $points |") } }
    println()
}
private fun Board.show(){
    print("   ")
    repeat(BOARD_SIZE.value){ print(" ${POSSIBLE_COL[it]} ") }
    println()
    printCurrLine(BOARD_SIZE.value)
    Position.values.forEach{pos ->
        print(" ${boardCells[pos]?.simbol ?: separator} ")
        if (pos.col == BOARD_SIZE.value)
            println().also { if (pos.line-1 > 0) printCurrLine(pos.line-1) }
    }
}
private fun printCurrLine(line: Int)=
    if (line >= 10) print("$line ")
    else print(" $line ")