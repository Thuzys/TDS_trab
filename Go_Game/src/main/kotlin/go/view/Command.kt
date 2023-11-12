package go.view

import go.model.*
import go.storage.Storage

abstract class Command(){
    open fun execute(args: List<String>, game: Game): Game = throw IllegalStateException("Game over.")
    open val isToFinish = false
}
object Play: Command(){
    override fun execute(args: List<String>, game: Game): Game {
        val arg = requireNotNull(args.firstOrNull()){"Missing index."}
        return game.play(arg.toPosition())
    }
}

fun getCommands(storage: Storage<String, Game>): Map<String, Command>{
    return mapOf(
        "PLAY" to Play,
        "NEW" to object : Command(){
            override fun execute(args: List<String>, game: Game): Game = game.newBoard()
        },
        "PASS" to object : Command(){
            override fun execute(args: List<String>, game: Game): Game = game.pass()
        },
        "SAVE" to object : Command(){
            override fun execute(args: List<String>, game: Game): Game {
                require(args.isNotEmpty()) {"Missing name."}
                requireNotNull(game.board) {"Game not started."}
                val name = args[0]
                require(name.isNotEmpty()) {"Name must not be empty."}
                return game.also { storage.create(name, game) }
            }
        },
        "LOAD" to object : Command(){
            override fun execute(args: List<String>, game: Game): Game {
                val name = requireNotNull(args.firstOrNull()) {"Missing name."}
                return checkNotNull(storage.read(name)) {"Game $name not found."}
            }
        },
        "EXIT" to object: Command(){
            override val isToFinish = true
        },
        "DELETE" to object : Command(){
            override fun execute(args: List<String>, game: Game): Game {
                val name = requireNotNull(args.firstOrNull()) {"Missing name."}
                return game.also { storage.delete(name) }
            }
        },
        "UPDATE" to object : Command(){
            override fun execute(args: List<String>, game: Game): Game {
                val name = requireNotNull(args.firstOrNull()) {"Missing name."}
                return game.also { storage.update(name, it) }
            }
        }
    )
}