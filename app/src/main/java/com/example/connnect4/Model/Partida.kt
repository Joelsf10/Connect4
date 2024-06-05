package com.example.connnect4.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "partidas")
data class Partida(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val jugador1: String,
    val jugador2: String,
    val resultado: String,
    val fecha: Date
)
