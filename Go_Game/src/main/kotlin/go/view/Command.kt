package go.view

import go.model.Board


abstract class Command(val argSyntax: String = ""){
    open fun execute(args: List<String>, board: Board?): Board = throw IllegalStateException("Game over.")
    open val isToFinish = false
}
object Play: Command("idx"){
    override fun execute(args: List<String>, board: Board?): Board {
        checkNotNull(board){"Game not started."}
        val arg = requireNotNull(args.firstOrNull()){"Missing index."}
        val idx = requireNotNull(arg.toIntOrNull()){"Invalid index $arg."}

        return board.play(idx.toPosition())
    }
}

fun getCommand(): Map<String, Command>{
    return mapOf<String, Command>(
        "PLAY" to Play,
        "NEW" to object : Command(){
            override fun execute(args: List<String>, board: Board?): Board = Board()
        },
        "PASS" to object : Command(){
            override fun execute(args: List<String>, board: Board?): Board {
                TODO("not implementd yet")
            }
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