package go.model

enum class Player(val simbol: Char){
    O('O'),
    X('#');
    val other get() = if(this == X) O else X
}

fun BoardCells.tellWinner():Player{
    TODO()
}