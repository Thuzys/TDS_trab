import go.model.Game
import go.storage.GameSerializer
import go.storage.TextFileStore
import go.view.getCommands
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import go.view.readCommandLine
import go.view.show
fun main() {
    var game = Game()
    val storage = TextFileStore<String, Game>("saves", GameSerializer)
    val commands = getCommands(storage)
    while(true){
        val (name,args)  = readCommandLine()
        val cmd = commands[name]
        if(cmd==null)println("Invalid Command $name")
        else
            try {
                if( cmd.isToFinish) break
                game = cmd.execute(args, game)
            }catch (e: IllegalStateException){
                println(e.message)
            }
            catch (e: IllegalArgumentException){
                println(e.message)
            }
        game.show()
    }
}
