package com.example.connect4

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú Principal") },
                actions = {
                    IconButton(onClick = { navController.navigate("config") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) { paddingValues ->
        Surface(color = MaterialTheme.colors.background, modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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
                    onClick = { navController.navigate("gameScreen") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = ButtonDefaults.elevation(defaultElevation = 8.dp)
                ) {
                    Text("Jugar Partida", fontSize = 18.sp)
                }
                Button(
                    onClick = { navController.navigate("gameConsult") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = ButtonDefaults.elevation(defaultElevation = 8.dp)
                ) {
                    Text("Consultar Partidas", fontSize = 18.sp)
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
}


