package go.view

import go.model.*


abstract class Command{
    open fun execute(args: List<String>, board: Board?): Board = throw IllegalStateException("Game over.")
    open val isToFinish = false
}
object Play: Command(){
    override fun execute(args: List<String>, board: Board?): Board {
        checkNotNull(board){"Game not started."}
        val arg = requireNotNull(args.firstOrNull()){"Missing index."}
        return board.play(arg.toPosition())
    }
}

fun getCommands(): Map<String, Command>{
    return mapOf(
        "PLAY" to Play,
        "NEW" to object : Command(){
            override fun execute(args: List<String>, board: Board?): Board = Board()
        },
        "PASS" to object : Command(){
            override fun execute(args: List<String>, board: Board?): Board = board.pass()
        },
        "SAVE" to object : Command(){
            override fun execute(args: List<String>, board: Board?): Board {
                TODO("not implement yet")
            }
        },
        "LOAD" to object : Command(){
            override fun execute(args: List<String>, board: Board?): Board {
                TODO("not implemented yet")
            }
        },
        "EXIT" to object: Command(){
            override val isToFinish = true
        }
    )
}