package com.example.connect4

import androidx.lifecycle.*
import com.example.connnect4.Database.AppDatabase
import com.example.connnect4.Model.Partida
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameConsultViewModel(private val database: AppDatabase) : ViewModel() {
    private val _partidas = MutableLiveData<List<Partida>>()
    val partidas: LiveData<List<Partida>> = _partidas

    fun loadPartidas() {
        viewModelScope.launch(Dispatchers.IO) {
            _partidas.postValue(database.partidaDao().getAllPartidas())
        }
    }
}

class GameConsultViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameConsultViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameConsultViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}




