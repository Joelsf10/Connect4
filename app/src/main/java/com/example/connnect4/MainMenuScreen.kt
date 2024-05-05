package com.example.connect4

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.connnect4.R

@Composable
fun MainMenuScreen(navController: NavController) {
    val context = LocalContext.current
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Usar Image en lugar de Text para el título
            Image(
                painter = painterResource(id = R.drawable.connect4),
                contentDescription = "Logo de Connect4",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
            )
            Button(
                onClick = { navController.navigate("help") },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 8.dp)
            ) {
                Text("Ayuda", fontSize = 18.sp)
            }
            Button(
                onClick = { navController.navigate("config") },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 8.dp)
            ) {
                Text("Jugar Partida", fontSize = 18.sp)
            }
            Button(
                onClick = {
                    if (context is ComponentActivity) {
                        context.finish()  // Llama a finish para cerrar la aplicación
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 8.dp)
            ) {
                Text("Salir", fontSize = 18.sp)
            }
        }
    }
}

