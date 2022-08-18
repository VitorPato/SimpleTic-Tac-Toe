package tictactoe

import javax.swing.table.TableColumn

fun main() {
    ticTacToe()
}

fun ticTacToe() {
    val board = initBoard()
    drawBoard(board)
    var currentPlayer = 'X'

    while (true) {
        updateBoard(board, currentPlayer)
        drawBoard(board)
        if (gameOver(board)) {
            break
        }
        currentPlayer = updatePlayer(currentPlayer)
    }

}

fun updatePlayer(currentPlayer: Char): Char {
    if (currentPlayer == 'X') {
        return 'O'
    } else {
        return 'X'
    }
}
fun playerMove(board: Array<CharArray>): Pair<Int, Int> {
    var x_coordinate = 0
    var y_coordinate = 0

    while (true) {
        val userInput = readln()
        val a = userInput.first()
        val b = userInput.last()

        if (!a.isDigit() || !b.isDigit()) {
            println("You should enter numbers!")
            continue
        }

        x_coordinate = a.digitToInt()
        y_coordinate = b.digitToInt()

        if (x_coordinate !in 1..3 || y_coordinate !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            continue
        }

        if (board[x_coordinate - 1][y_coordinate - 1] != '_') {
            println("This cell is occupied! Choose another one!")
            continue
        }
        break
    }

    return Pair(x_coordinate - 1, y_coordinate - 1)
}

fun updateBoard(board: Array<CharArray>, playerCharacter: Char = 'X') {
    val (x, y) = playerMove(board)
    board[x][y] = playerCharacter
}

fun initBoard(): Array<CharArray> {
    val board = arrayOf(
        CharArray(3) {'_'},
        CharArray(3) {'_'},
        CharArray(3) {'_'}
    )
    return board
}


fun drawBoard(board: Array<CharArray>) {
    println("---------")
    for (row in board) {
        print("| ")
        for (element in row) {
            print("$element ")
        }
        println("|")
    }
    println("---------")
}

fun gameOver(board: Array<CharArray>): Boolean {

//    if (impossible(board)) {
//        return true
//    }

    if (playerVictory(board)) {
        return true
    }

    if (draw(board)) {
        return true
    }

//    if (gameNotFinished(board)) {
//        return true
//    }


    return false
}


fun playerVictory(board: Array<CharArray>): Boolean {
    // Rows
    for (i in 0..2) {
        if (board[i].all { it == 'O' } || board[i].all { it == 'X' }) {
            println("${board[i].first()} wins")
            return true
        }
    }

    // Columns
    for (j in 0..2) {
        if (board[0][j] != '_' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
            println("${board[0][j]} wins")
            return true
        }
    }

    // Main diagonal
    if (board[0][0] != '_' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
        println("${board[0][0]} wins")
        return true
    }

    if (board[0][2] != '_' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
        println("${board[0][2]} wins")
        return true
    }

    return false
}

fun specificPlayerVictory(board: Array<CharArray>, player: Char): Boolean {
    // Rows
    for (i in 0..2) {
        if (board[i].all {it == player}) {
            println("${board[i].first()} wins")
            return true
        }
    }

    // Columns
    for (j in 0..2) {
        if (board[0][j] == player && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
            println("${board[0][j]} wins")
            return true
        }
    }

    // Main diagonal
    if (board[0][0] == player && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
        println("${board[0][0]} wins")
        return true
    }

    if (board[0][2] == player && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
        println("${board[0][2]} wins")
        return true
    }

    return false
}

fun draw(board: Array<CharArray>): Boolean {
    var counter = 0
    for (row in board) {
        for (element in row) {
            if (element in listOf('X', 'O')) {
                counter++
            }
        }
    }

    if (counter == 9) {
        println("Draw")
        return true
    }

    return false
}

fun gameNotFinished(board: Array<CharArray>): Boolean {
    var counter = 0
    for (row in board) {
        for (element in row) {
            if (element == '_') {
                counter++
            }
        }
    }

    if (counter > 0) {
        println("Game not finished")
        return true
    }

    return false
}

fun impossible(board: Array<CharArray>): Boolean {

    // Board players won
    if (specificPlayerVictory(board, 'X') && specificPlayerVictory(board, 'O')) {
        println("Impossible")
        return true
    }

    var firstCounter = 0
    var secondCounter = 0

    for (row in board) {
        for (element in row) {
            if (element == 'X') {
                firstCounter++
            } else if (element == 'O') {
                secondCounter++
            }
        }
    }

    if (firstCounter - secondCounter !in -1..1) {
        println("Impossible")
        return true
    }

    return false
}