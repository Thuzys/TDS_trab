import go.model.Board
import go.view.getCommands
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import go.view.readCommandLine
import go.view.show
fun main() {

    var board: Board? = null

    val commands = getCommands()
    while(true){
        val (name,args)  = readCommandLine() //play 9a
        val cmd = commands[name]
        if(cmd==null)println("Invalid Command $name")
        else
            try {
                if( cmd.isToFinish) break
                board = cmd.execute(args, board)
            }catch (e: IllegalStateException){
                println(e.message)
            }
            catch (e: IllegalArgumentException){
                println(e.message)
            }
        board?.show()
    }
}
