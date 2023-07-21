
interface TicTacToeGame {
    var currentPlayer: Player
    var boardSize: Int
    fun initGame()
    fun boardIsEmpty(): Boolean
    fun move(point: Pair<Int, Int>)
}

typealias Player = Int
