package com.example.connect4

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.system.measureTimeMillis

class GameViewModel(
    val boardWidth: Int,
    val boardHeight: Int,
    playerAlias: String = "Player" // Valor predeterminado para playerAlias
) : ViewModel() {
    var board = mutableStateOf(Array(boardHeight) { Array(boardWidth) { "" } })
    var currentPlayer = mutableStateOf(playerAlias)
    var winner = mutableStateOf<String?>(null)
    var gameActive = mutableStateOf(true)

    fun play(column: Int) {
        if (!gameActive.value || winner.value != null) return

        val newBoard = board.value.map { it.copyOf() }.toTypedArray()

        for (row in boardHeight - 1 downTo 0) {
            if (newBoard[row][column].isEmpty()) {
                newBoard[row][column] = currentPlayer.value
                if (checkForWin(row, column, newBoard)) {
                    winner.value = currentPlayer.value
                    gameActive.value = false
                } else {
                    togglePlayer()
                }
                break
            }
        }
        board.value = newBoard
    }

    private fun togglePlayer() {
        currentPlayer.value = if (currentPlayer.value == "Player") "System" else "Player"
    }

    private fun checkForWin(row: Int, column: Int, board: Array<Array<String>>): Boolean {
        val directions = listOf(
            Direction(0, 1),
            Direction(1, 0),
            Direction(1, 1),
            Direction(1, -1)
        )

        for (dir in directions) {
            val count = 1 + countDirection(row, column, dir, board) + countDirection(row, column, dir.invert(), board)
            if (count >= 4) {
                return true
            }
        }
        return false
    }

    private fun countDirection(row: Int, column: Int, direction: Direction, board: Array<Array<String>>): Int {
        var r = row + direction.deltaRow
        var c = column + direction.deltaColumn
        var count = 0

        while (r in 0 until boardHeight && c in 0 until boardWidth && board[r][c] == currentPlayer.value) {
            count++
            r += direction.deltaRow
            c += direction.deltaColumn
        }
        return count
    }

    data class Direction(val deltaRow: Int, val deltaColumn: Int) {
        fun invert() = Direction(-deltaRow, -deltaColumn)
    }
}








