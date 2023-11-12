package go.storage

import go.model.Game
import go.model.Player

object GameSerializer : Seriealizer<Game> {
    override fun serialize(data: Game)  = buildString {
        appendLine( data.captured.entries.joinToString(" "){ (player, pts) ->
            "$player=$pts"
        } )
        appendLine( data.firstPlayer )
        data.board?.let { appendLine(BoardSerializer.serialize(it)) }
        appendLine( data.score.entries.joinToString(" ") { (player, score) ->
            "$player=$score"
        })
    }

    override fun deserialize(text: String): Game =
        text.split("\n").let { (players, firstPlayer, board, score) -> Game(
            firstPlayer = Player.valueOf(firstPlayer),
            captured = players.split(" ").map { it.split("=") }
                .associate { (player, points) ->
                    Player.entries.firstOrNull{it.name == player} to points.toInt()
                },
            board = if (board.isBlank()) null else BoardSerializer.deserialize(board),
            score = score.split(" ").map { it.split("=") }
                .associate { (player, score) -> Player.entries.firstOrNull{ it.name == player} to score.toDouble() }
        ) }
}