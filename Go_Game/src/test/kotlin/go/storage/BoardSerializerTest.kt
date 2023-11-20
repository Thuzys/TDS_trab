package go.storage

import go.model.*
import go.storage.BoardSerializer.deserialize
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


    @Test
    fun `deserialize board run`(){
        val map = mapOf(
            Position("7A") to Player.X,
            Position("7B") to Player.X,
            Position("7E") to Player.O
        )
        val expected = BoardRun(
            map,
            Player.O,
            false,
            null
        )
        val actualBoard = deserialize("run O false null | 7A:X 7B:X 7E:O")

        assert(actualBoard is BoardRun)

        assertEquals(expected.boardCells, actualBoard.boardCells)
        assertEquals(expected.pass, (actualBoard as BoardRun).pass)
        assertEquals(expected.removed, actualBoard.removed)
        assertEquals(expected.turn, actualBoard.turn)
    }
    @Test

    fun `deserialize board win`(){
        val map = mapOf(
            Position("7A") to Player.X,
            Position("7B") to Player.X,
            Position("7E") to Player.O
        )
        val expected = BoardWin(
            map,
            Player.X
        )
        val actualBoard = deserialize("win X null null | 7A:X 7B:X 7E:O")

        assert(actualBoard is BoardWin)

        assertEquals(expected.boardCells, actualBoard.boardCells)
        assertEquals(expected.winner, (actualBoard as BoardWin).winner)
    }

    @Test
    fun `deserialize board draw`(){
        val map = mapOf(
            Position("7A") to Player.X,
            Position("7B") to Player.X,
            Position("7E") to Player.O
        )
        val expected = BoardDraw(
            map
        )
        val actualBoard = deserialize("draw null null null | 7A:X 7B:X 7E:O")

        assert(actualBoard is BoardDraw)

        assertEquals(expected.boardCells, actualBoard.boardCells)
    }

}