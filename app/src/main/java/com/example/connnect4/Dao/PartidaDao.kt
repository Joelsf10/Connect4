package com.example.connnect4.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.connnect4.Model.Partida

@Dao
interface PartidaDao {
    @Insert
    suspend fun insert(partida: Partida)

    @Query("SELECT * FROM partidas")
    suspend fun getAllPartidas(): List<Partida>
}