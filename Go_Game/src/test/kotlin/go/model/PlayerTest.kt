package go.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException
import kotlin.test.assertFailsWith

class PlayerTest{

    @Test
    fun `get player`(){
        val playerX = getPlayer("X")
        val playerO = getPlayer("O")
        val invalidPlayer = getPlayer("invalid")

        assertEquals(Player.X, playerX)
        assertEquals(Player.O, playerO)
        assertEquals(null, invalidPlayer)
    }

    @Test
    fun `get non nullable player`() {
        val playerX = getNonNullablePlayer("X")
        val playerO = getNonNullablePlayer("O")
//       val invalidPlayer = getNonNullablePlayer("invalid")

        assertEquals(Player.X, playerX)
        assertEquals(Player.O, playerO)
        assertFailsWith<IllegalStateException> {                                 //nao sei se podemos user este assert
            getNonNullablePlayer("Invalid")
        }
    }
}