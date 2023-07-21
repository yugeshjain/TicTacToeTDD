class TicTacToeGameImpl(override var currentPlayer: Player) : TicTacToeGame {
    private lateinit var board: Array<Array<Int>>

    override fun initGame() {
        board = Array(3) { Array(3) { -1 } }
    }

    override fun boardIsEmpty(): Boolean {
        var isEmpty = true
        board.forEach {
            it.forEach { value ->
                if (value != -1) {
                    isEmpty = false
                }
            }
        }
        return isEmpty
    }

    override fun move(point: Pair<Int, Int>) {
        return kotlin.run {
            board[point.first].let { internal ->
                internal[point.second].takeIf { it == -1 }?.let {
                    internal[point.second] = currentPlayer
                    val newPlayer = switchPlayer()
                    hasWinner()
                    newPlayer
                } ?: kotlin.run {
                    throw AlreadyHaveValue("We already have a value at (${point.first},${point.second})")
                }
            }
        }
    }

    private fun hasWinner() {
        verifyRows()
        verifyColumns()
        verifyTopLeftToBottomRight()
        verifyBottomLeftToTopRight()
        draw()
    }

    private fun draw() {
        var filled = -1
        board.forEach { array ->
            if (array.all { it != -1 }) {
                filled = 0
            }
        }
        if (filled != -1) {
            throw DrawException()
        }
    }

    private fun verifyBottomLeftToTopRight() {
        val leftToRight = mutableListOf<Int>()
        var row = 0
        var column = board.size.minus(1)
        while (column != -1) {

            val current = board[row][column]
            leftToRight.add(current)
            row += 1
            column -= 1

            if (leftToRight.size == 3 && leftToRight.all { it == 1 }) {
                throw WinnerDeclared("We have a winner 1!")
            }
            if (leftToRight.size == 3 && leftToRight.all { it == 0 }) {
                throw WinnerDeclared("We have a winner 0!")
            }
            if (leftToRight.size == 3) {
                leftToRight.clear()
            }
        }
    }

    private fun verifyTopLeftToBottomRight() {
        val leftToRight = mutableListOf<Int>()
        var size = board.size.minus(1)

        while (size != -1) {
            val current = board[size][size]
            leftToRight.add(current)
            size -= 1

            if (leftToRight.size == 3 && leftToRight.all { it == 1 }) {
                throw WinnerDeclared("We have a winner 1!")
            }
            if (leftToRight.size == 3 && leftToRight.all { it == 0 }) {
                throw WinnerDeclared("We have a winner 0!")
            }
            if (leftToRight.size == 3) {
                leftToRight.clear()
            }
        }
    }

    private fun verifyColumns() {
        val columnItem = mutableListOf<Int>()

        var size = board.size.minus(1)
        while (size != -1) {
            board.forEachIndexed { index, _ ->
                columnItem.add(board[index][size])
            }
            size -= 1
            if (columnItem.size == 3 && columnItem.all { it == 1 }) {
                throw WinnerDeclared("We have a winner 1!")
            }
            if (columnItem.size == 3 && columnItem.all { it == 0 }) {
                throw WinnerDeclared("We have a winner 0!")
            }
            columnItem.clear()
        }
    }

    private fun verifyRows() {
        val rowItem = mutableListOf<Int>()
        board.forEachIndexed { x, column ->
            column.forEachIndexed { y, value ->
                println("$x,$y = $value")
                rowItem.add(value)
            }
            if (rowItem.size == 3 && rowItem.all { it == 1 }) {
                throw WinnerDeclared("We have a winner 1!")
            }
            if (rowItem.size == 3 && rowItem.all { it == 0 }) {
                throw WinnerDeclared("We have a winner 0!")
            }
            rowItem.clear()
        }
    }

    private fun switchPlayer(): Player {
        currentPlayer = if (currentPlayer == 0) {
            1
        } else {
            0
        }
        return currentPlayer
    }
}
