package com.example.connect4

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ConfigViewModel : ViewModel() {
    val alias = MutableStateFlow("")  // Alias del jugador
    val gridSize = MutableStateFlow(4f)  // Tamaño de la parrilla
    val isTimeControlEnabled = MutableStateFlow(false)  // Si el control del tiempo está habilitado
    val maxTime = MutableStateFlow("")  // Tiempo máximo para cada movimiento

    fun validateMaxTime(input: String): Boolean {
        return input.toIntOrNull()?.let {
            it in 1..300  // Asegura que el tiempo esté entre 1 y 300 segundos
        } ?: false
    }
}



