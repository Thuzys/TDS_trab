package go.view
data class CommandLine(val name: String, val args: List<String>)
fun readComandLine(): CommandLine {
    println(">")
    val line = readln().split(" ").filter { it.isNotBlank() }
    return if (line.isEmpty()) readComandLine() else CommandLine(line.first(), line.drop(1))
}