package go.storage

import go.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardSerializerTest{


    @Test
    fun `serialize BoardRun`() {
        val board = BoardRun(
            mapOf(
                Position("1A") to Player.X,
                Position("2B") to Player.O
            ),
            turn = Player.X,
            pass = false,
            removed = Player.O
        )

        val expectedSerialization = "run X false O | 1A:X 2B:O"
        val actualSerialization = BoardSerializer.serialize(board)

        assertEquals(expectedSerialization, actualSerialization)
    }

    @Test
    fun `serialize BoardWin`() {
        val board = BoardWin(
            mapOf(
                Position("7B") to Player.X,
                Position("5H") to Player.O
            ),
            winner = Player.O,
        )

        val expectedSerialization = "win O | 7B:X 5H:O"
        val actualSerialization = BoardSerializer.serialize(board)

        assertEquals(expectedSerialization, actualSerialization)
    }

    @Test
    fun `serialize BoardDraw`() {
        val board = BoardDraw(
            mapOf()
        )

        val expectedSerialization = "draw | "
        val actualSerialization = BoardSerializer.serialize(board)

        assertEquals(expectedSerialization, actualSerialization)


        val board2 = BoardDraw(
            mapOf(
                Position("7B") to Player.O,
                Position("9A") to Player.X,
                Position("8E") to Player.O
            )
        )

        val expectedSerialization2 = "draw | 7B:O 9A:X 8E:O"
        val actualSerialization2 = BoardSerializer.serialize(board2)

        assertEquals(expectedSerialization2, actualSerialization2)
    }

}