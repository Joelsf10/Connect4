package com.example.connect4

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.connnect4.R

@Composable
fun HelpScreen(navController: NavController) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Ayuda",
                fontSize = 34.sp,
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = MaterialTheme.typography.h4.fontWeight,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = "Connect4 es un juego de estrategia para dos jugadores en el que el objetivo es conectar cuatro fichas del mismo color en línea, ya sea horizontal, vertical o diagonalmente, antes que tu oponente.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.juego),
                contentDescription = "Juego",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 20.dp)
            )

            Button(
                onClick = { navController.navigate("main") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Volver al Menú Principal",
                    fontSize = 18.sp
                )
            }
        }
    }
}



