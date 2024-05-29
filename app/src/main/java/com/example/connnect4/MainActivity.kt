package com.example.connnect4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.connect4.ConfigScreen
import com.example.connect4.ConfigViewModel
import com.example.connect4.GameScreen
import com.example.connect4.HelpScreen
import com.example.connect4.MainMenuScreen
import com.example.connect4.ResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val configViewModel = viewModel<ConfigViewModel>()  // Así compartimos el ViewModel entre pantallas

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainMenuScreen(navController) }
        composable("help") { HelpScreen(navController) }
        composable("config") { ConfigScreen(navController, configViewModel) }
        composable("gameScreen") {
            GameScreen(navController, configViewModel.gridSize.value.toInt())
        }
        composable("resultScreen") {
            ResultScreen(navController = navController, configViewModel.alias.value)  // Añade aquí los argumentos necesarios si los hay
        }
    }
}


