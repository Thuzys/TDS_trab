package go.model

enum class Player(val simbol: Char, val color: String){
    O('O', "White"),
    X('#', "Black");
    val other get() = if(this == X) O else X
}