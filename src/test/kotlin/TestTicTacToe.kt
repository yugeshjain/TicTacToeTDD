import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class TestTicTacToe {

    private val ticTacToe: TicTacToeGameImpl by lazy {
        TicTacToeGameImpl(
            currentPlayer = 0,
            boardSize = 4
        )
    }

    @Test
    fun test_WhenInitCalled_ThenEmptyArray() = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
    }


    @Test
    fun test_WhenCurrentPlayerMove_weHaveOneValue(): Unit = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
        move(Pair(0, 0))
        assert(currentPlayer == 1)
        assert(boardIsEmpty().not())
        assertFailsWith<AlreadyHaveValue> {
            move(Pair(0, 0))
        }
    }

    @Test
    fun test_WhenRowOneIsAllZeroThen_PlayerZeroWins() = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
        val turns = firstRowDataSet()
        val exception = assertFailsWith<WinnerDeclared> {
            turns.forEach {
                move(it)
            }
        }
        assert(exception.message == "We have a winner 0!")
    }

    @Test
    fun test_WhenRowTwoIsAllZeroThen_PlayerZeroWins() = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
        val turns = secondRowDataSet()
        val exception = assertFailsWith<WinnerDeclared> {
            turns.forEach {
                move(it)
            }
        }
        assert(exception.message == "We have a winner 0!")
    }

    @Test
    fun test_WhenRowThreeIsAllZeroThen_PlayerZeroWins() = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
        val turns = thirdRowDataSet()
        val exception = assertFailsWith<WinnerDeclared> {
            turns.forEach {
                move(it)
            }
        }
        assert(exception.message == "We have a winner 0!")
    }

    @Test
    fun test_WhenColumnOneIsAllZeroThen_PlayerZeroWins() = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
        val turns = firstColumnDataSet()
        val exception = assertFailsWith<WinnerDeclared> {
            turns.forEach {
                move(it)
            }
        }
        assert(exception.message == "We have a winner 0!")
    }

    @Test
    fun test_WhenColumnTwoIsAllZeroThen_PlayerZeroWins() = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
        val turns = secondColumnDataSet()
        val exception = assertFailsWith<WinnerDeclared> {
            turns.forEach {
                move(it)
            }
        }
        assert(exception.message == "We have a winner 0!")
    }

    @Test
    fun test_WhenColumnThreeIsAllZeroThen_PlayerZeroWins() = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
        val turns = thirdColumnDataSet()
        val exception = assertFailsWith<WinnerDeclared> {
            turns.forEach {
                move(it)
            }
        }
        assert(exception.message == "We have a winner 0!")
    }

    @Test
    fun test_WhenTopLeftToBottomRightDiagonalIsAllZero_ThenPlayerZeroWins() = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
        val turns = topLeftToBottomRightDataSet()
        val exception = assertFailsWith<WinnerDeclared> {
            turns.forEach {
                move(it)
            }
        }
        assert(exception.message == "We have a winner 0!")
    }

    @Test
    fun test_WhenBottomLeftToTopRightDiagonalIsAllZero_ThenPlayerZeroWins() = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
        val turns = bottomLeftToTopRightDataSet()
        val exception = assertFailsWith<WinnerDeclared> {
            turns.forEach {
                move(it)
            }
        }
        assert(exception.message == "We have a winner 0!")
    }

    @Test
    fun test_WhenNoThreeConsecutivePlacesAreSame_ThenDraw(): Unit = with(ticTacToe) {
        assert(currentPlayer == 0)
        initGame()
        assert(boardIsEmpty())
        val turns = drawDataSet()
        assertFailsWith<DrawException> {
            turns.forEach {
                move(it)
            }
        }
    }

    // Moved For test cases
    private fun firstRowDataSet() = listOf(
        Pair(0, 0), Pair(1, 1),
        Pair(0, 1), Pair(1, 2),
        Pair(0, 2), Pair(2, 2),
        Pair(0, 3)
    )

    private fun secondRowDataSet() = listOf(
        Pair(1, 0), Pair(0, 1),
        Pair(1, 1), Pair(0, 2),
        Pair(1, 2), Pair(2, 2),
        Pair(1, 3)
    )

    private fun thirdRowDataSet() = listOf(
        Pair(2, 0), Pair(0, 1),
        Pair(2, 1), Pair(0, 2),
        Pair(2, 2), Pair(1, 2),
        Pair(2, 3)
    )

    private fun firstColumnDataSet() = listOf(
        Pair(0, 0), Pair(0, 1),
        Pair(1, 0), Pair(1, 2),
        Pair(2, 0), Pair(2, 2),
        Pair(3, 0)
    )

    private fun secondColumnDataSet() = listOf(
        Pair(0, 1), Pair(0, 0),
        Pair(1, 1), Pair(1, 2),
        Pair(2, 1), Pair(2, 2),
        Pair(3, 1)
    )

    private fun thirdColumnDataSet() = listOf(
        Pair(0, 2), Pair(0, 0),
        Pair(1, 2), Pair(1, 0),
        Pair(2, 2), Pair(2, 1),
        Pair(3, 2)
    )

    private fun topLeftToBottomRightDataSet() = listOf(
        Pair(0, 0), Pair(0, 1),
        Pair(1, 1), Pair(1, 2),
        Pair(2, 2), Pair(2, 1),
        Pair(3, 3)
    )

    private fun bottomLeftToTopRightDataSet() = listOf(
        Pair(0, 3), Pair(0, 1),
        Pair(1, 2), Pair(1, 1),
        Pair(2, 1), Pair(2, 2),
        Pair(3, 0)
    )

    private fun drawDataSet() = listOf(
        Pair(0, 0), Pair(0, 1),
        Pair(0, 2), Pair(1, 1),
        Pair(1, 0), Pair(1, 2),
        Pair(2, 1), Pair(2, 0),
        Pair(2, 2), Pair(0, 3),
        Pair(1, 3), Pair(2, 3),
        Pair(3, 0), Pair(3, 1),
        Pair(3, 2), Pair(3, 3)
    )
}
