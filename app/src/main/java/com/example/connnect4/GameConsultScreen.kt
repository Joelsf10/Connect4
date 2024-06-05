package com.example.connect4

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.connnect4.Model.Partida
import java.text.SimpleDateFormat

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameConsultScreen(navController: NavController, viewModel: GameConsultViewModel) {
    val partidas = viewModel.partidas.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Consultar Partidas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(partidas.value) { partida ->
                PartidaItem(partida)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadPartidas()
    }
}

@Composable
fun PartidaItem(partida: Partida) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Jugador 1: ${partida.jugador1}")
            Text(text = "Jugador 2: ${partida.jugador2}")
            Text(text = "Resultado: ${partida.resultado}")
            Text(text = "Fecha: ${SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(partida.fecha)}")
        }
    }
}








