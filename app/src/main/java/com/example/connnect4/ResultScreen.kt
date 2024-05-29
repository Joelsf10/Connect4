package com.example.connect4

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ResultScreen(navController: NavController, winnerName: String) {
    val context = LocalContext.current
    val currentTime by remember { mutableStateOf(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())) }
    var logText by remember { mutableStateOf("Ganador de la partida: $winnerName.") }
    var emailRecipient by remember { mutableStateOf("correo@example.com") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Resultados de la Partida", style = MaterialTheme.typography.h6.copy(fontSize = 20.sp))
        Spacer(Modifier.height(20.dp))
        Text("Finalizado el $currentTime", style = MaterialTheme.typography.body1.copy(fontSize = 16.sp))
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = logText,
            onValueChange = { logText = it },
            label = { Text("Log de la Partida") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 10,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = emailRecipient,
            onValueChange = { emailRecipient = it },
            label = { Text("Email del destinatario") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate("config")
            }
        ) {
            Text("Nueva Partida", fontSize = 16.sp)
        }
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {
                (context as ComponentActivity).finish()
            }
        ) {
            Text("Salir", fontSize = 16.sp)
        }
    }
}






