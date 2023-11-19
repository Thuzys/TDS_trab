package go.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameTest{
    @Test
    fun `New board test`(){
        var game = Game()
        assertNull(game.board)
        game = game.newBoard()
        assertNotNull(game.board)
    }

    @Test
    fun tellWinner(){
        val map = mutableMapOf<Player?, Int>()
        map[Player.X] = 9
        map[Player.O] = 2
        var game = Game()
        game = game.newBoard()
        game = game.copy(captured = map)
        game = game.pass()
        game = game.pass()
        assert(game.board is BoardWin)
        assertEquals(Player.X, (game.board as BoardWin).winner)
    }

}