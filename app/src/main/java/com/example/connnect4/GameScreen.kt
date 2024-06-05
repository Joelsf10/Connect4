package com.example.connect4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Connect4Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC5),
            background = Color(0xFF121212),
            surface = Color(0xFF333333),
            onPrimary = Color.White,
            onSecondary = Color.Black,
            onBackground = Color.White,
            onSurface = Color.White
        ),
        typography = Typography(defaultFontFamily = FontFamily.Default),
        shapes = Shapes(small = RoundedCornerShape(4.dp), medium = RoundedCornerShape(8.dp), large = RoundedCornerShape(16.dp)),
        content = content
    )
}

@Composable
fun GameScreen(navController: NavController, gridSize: Int) {
    val gameViewModel: GameViewModel = viewModel { GameViewModel(gridSize, gridSize) }
    Connect4Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            if (isTablet()) {
                TwoPanelLayout(gameViewModel, navController)
            } else {
                SinglePanelLayout(gameViewModel, navController)
            }
        }
    }
}

@Composable
fun SinglePanelLayout(viewModel: GameViewModel, navController: NavController) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Connect 4 Game",
            style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground)
        )
        Spacer(Modifier.height(16.dp))
        Board(viewModel)
        Spacer(Modifier.height(16.dp))
        GameStatus(viewModel, navController)
    }
}

@Composable
fun TwoPanelLayout(viewModel: GameViewModel, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            SinglePanelLayout(viewModel, navController)
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Información de progreso",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Jugador actual: ${viewModel.currentPlayer.value}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Estado del juego: ${if (viewModel.gameActive.value) "Activo" else "Finalizado"}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.smallestScreenWidthDp >= 600
}



@Composable
fun Board(viewModel: GameViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(viewModel.boardWidth), // Set the number of columns based on the board width
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        itemsIndexed(viewModel.board.value.flatten()) { index, cell ->
            val columnIndex = index % viewModel.boardWidth

            Button(
                onClick = {
                    if (viewModel.gameActive.value && cell.isEmpty()) {
                        viewModel.play(columnIndex)
                    }
                },
                enabled = viewModel.gameActive.value,
                modifier = Modifier
                    .padding(4.dp)
                    .aspectRatio(1f)
                    .background(color = Color.Transparent, shape = RoundedCornerShape(50)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = when (cell) {
                        "Player" -> Color.Red
                        "System" -> Color.Yellow
                        else -> Color.LightGray
                    },
                    contentColor = Color.White
                )
            ) {
                Text(text = if (cell.isEmpty()) "-" else cell, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun GameStatus(viewModel: GameViewModel, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        if (viewModel.gameActive.value) {
            Text(
                text = "Turn: ${viewModel.currentPlayer.value}",
                style = MaterialTheme.typography.h5.copy(fontSize = 18.sp),
                color = MaterialTheme.colors.onSurface
            )
        } else {
            Text(
                text = "Game Over: ${viewModel.winner.value ?: "It's a Draw!"} Wins!",
                style = MaterialTheme.typography.h5.copy(fontSize = 18.sp),
                color = MaterialTheme.colors.onSurface
            )
            LaunchedEffect(Unit) {
                navController.navigate("resultScreen")
            }
        }
    }
}




