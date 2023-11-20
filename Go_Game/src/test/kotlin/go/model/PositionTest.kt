package go.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PositionTest{

    @Test
    fun `string to position`(){
        val pos9a = "9a".toPosition()
        val posa9 = "a9".toPosition()
        val pos1B = "1B".toPosition()
        val pos1b = "1b".toPosition()

        assertEquals(Position("9a"), pos9a)
        assertEquals(Position("a9"), posa9)
        assertEquals(Position("1B"), pos1B)
        assertEquals(Position("1b"), pos1b)
    }

    @Test
    fun `position get adjacent`(){
        val getAdj_9A = Position("9a").getAdj()
        val listOfAdj9a = listOf<Position?>(null, Position("9B"), Position("8A"), null)

        val getAdj_8B = Position("8b").getAdj()
        val listOfAdj8b = listOf<Position?>(Position("8A"), Position("9B"), Position("8C"), Position("7B"))

        assertTrue(listOfAdj9a.size == getAdj_9A.size && getAdj_9A.containsAll(listOfAdj9a))
        assertTrue(listOfAdj8b.size == getAdj_8B.size && getAdj_8B.containsAll(listOfAdj8b))
    }
}