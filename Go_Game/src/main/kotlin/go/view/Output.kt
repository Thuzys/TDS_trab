package go.view

import go.model.*

private const val separator = "."

fun Board.show(){
    print("   ")
    repeat(BOARD_SIZE){ print(" ${POSSIBLE_COL[it]} ") }
    println()
    printCurrLine(BOARD_SIZE)
    Position.values.forEach{pos ->
        print(" ${boardCells[pos]?.simbol ?: separator} ")
        if (pos.col == BOARD_SIZE)
            println().also { if (pos.line-1 > 0) printCurrLine(pos.line-1) }
    }
    println(
        when(this){
            is BoardRun -> "Turn: ${turn.simbol}"
            is BoardWin -> "Winner: ${winner.name}"
        }
    )
}

private fun printCurrLine(line: Int)=
    if (line >= 10) print("$line ")
    else print(" $line ")