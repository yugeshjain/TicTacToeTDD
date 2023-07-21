
interface TicTacToeGame {
    var currentPlayer: Player
    fun initGame()
    fun boardIsEmpty(): Boolean
    fun move(point: Pair<Int, Int>)
}

typealias Player = Int
