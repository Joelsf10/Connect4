package com.example.connect4

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.system.measureTimeMillis

class GameViewModel(val boardWidth: Int, val boardHeight: Int, val playerAlias: String) : ViewModel() {
    var board = mutableStateOf(Array(boardHeight) { Array(boardWidth) { "" } })
    var currentPlayer = mutableStateOf(playerAlias)  // Jugador inicial, alias del usuario
    var winner = mutableStateOf<String?>(null)  // Almacena el ganador, inicialmente null
    var gameActive = mutableStateOf(true)  // Estado del juego, inicialmente activo
    private var gameStartTime = System.currentTimeMillis()  // Hora de inicio del juego
    private var gameEndTime = mutableStateOf(0L)  // Hora de fin del juego


    fun play(column: Int) {
        if (!gameActive.value || winner.value != null) return

        for (row in boardHeight - 1 downTo 0) {
            if (board.value[row][column].isEmpty()) {
                board.value[row][column] = currentPlayer.value
                if (checkForWin(row, column)) {
                    winner.value = currentPlayer.value
                    gameActive.value = false
                    gameEndTime.value = System.currentTimeMillis()
                } else {
                    togglePlayer()
                }
                break
            }
        }
    }

    private fun togglePlayer() {
        currentPlayer.value = if (currentPlayer.value == playerAlias) "Sistema" else playerAlias
    }

    private fun checkForWin(row: Int, column: Int): Boolean {
        val directions = listOf(
            Direction(0, 1),  // Horizontal derecha
            Direction(1, 0),  // Vertical abajo
            Direction(1, 1),  // Diagonal derecha abajo
            Direction(1, -1)  // Diagonal izquierda abajo
        )

        for (dir in directions) {
            var count = 1  // Iniciar contador de fichas consecutivas
            count += countDirection(row, column, dir)
            count += countDirection(row, column, dir.invert())

            if (count >= 4) {
                gameEndTime.value = System.currentTimeMillis()
                return true  // Se encontraron cuatro fichas en línea
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

    fun getGameDuration(): Long = if (gameEndTime.value > 0) gameEndTime.value - gameStartTime else 0L

    data class Direction(val deltaRow: Int, val deltaColumn: Int) {
        fun invert() = Direction(-deltaRow, -deltaColumn)
    }
}



