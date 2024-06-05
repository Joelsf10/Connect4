package com.example.connect4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.connnect4.Database.AppDatabase

class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase
    private val gameConsultViewModel: GameConsultViewModel by viewModels {
        GameConsultViewModelFactory(database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "connect4-database"
        ).build()

        setContent {
            AppNavigation(gameConsultViewModel)
        }
    }
}

@Composable
fun AppNavigation(gameConsultViewModel: GameConsultViewModel) {
    val navController = rememberNavController()
    val configViewModel = viewModel<ConfigViewModel>()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainMenuScreen(navController) }
        composable("help") { HelpScreen(navController) }
        composable("config") { ConfigScreen(navController, configViewModel) }
        composable("gameScreen") {
            GameScreen(navController, configViewModel.gridSize.value.toInt())
        }
        composable("resultScreen") {
            ResultScreen(navController = navController, configViewModel.alias.value)
        }
        composable("gameConsultScreen") {
            GameConsultScreen(navController = navController, viewModel = gameConsultViewModel)
        }
    }
}




