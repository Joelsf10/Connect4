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

        for (row in boardHeight - 1 downTo 0) {
            if (board.value[row][column].isEmpty()) {
                board.value[row][column] = currentPlayer.value
                if (checkForWin(row, column)) {
                    winner.value = currentPlayer.value
                    gameActive.value = false
                } else {
                    togglePlayer()
                }
                break
            }
        }
    }

    private fun togglePlayer() {
        currentPlayer.value = if (currentPlayer.value == "Player") "System" else "Player"
    }

    private fun checkForWin(row: Int, column: Int): Boolean {
        val directions = listOf(
            Direction(0, 1),
            Direction(1, 0),
            Direction(1, 1),
            Direction(1, -1)
        )

        for (dir in directions) {
            val count = 1 + countDirection(row, column, dir) + countDirection(row, column, dir.invert())
            if (count >= 4) {
                return true
            }
        }
        return false
    }

    private fun countDirection(row: Int, column: Int, direction: Direction): Int {
        var r = row + direction.deltaRow
        var c = column + direction.deltaColumn
        var count = 0

        while (r in 0 until boardHeight && c in 0 until boardWidth && board.value[r][c] == currentPlayer.value) {
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







