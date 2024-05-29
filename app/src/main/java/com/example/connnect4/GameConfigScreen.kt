package com.example.connect4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ConfigScreen(navController: NavController, viewModel: ConfigViewModel = viewModel()) {
    val alias by viewModel.alias.collectAsState()
    val gridSize by viewModel.gridSize.collectAsState()
    val isTimeControlEnabled by viewModel.isTimeControlEnabled.collectAsState()
    val maxTime by viewModel.maxTime.collectAsState()
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración del Juego") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = { padding ->
            BoxWithConstraints {
                if (maxWidth < 600.dp) {
                    // Orientación vertical
                    VerticalConfigLayout(navController, viewModel, alias, gridSize, isTimeControlEnabled, maxTime, showError, { showError = it })
                } else {
                    // Orientación horizontal
                    HorizontalConfigLayout(navController, viewModel, alias, gridSize, isTimeControlEnabled, maxTime, showError, { showError = it })
                }
            }
        }
    )
}

@Composable
fun VerticalConfigLayout(
    navController: NavController,
    viewModel: ConfigViewModel,
    alias: String,
    gridSize: Float,
    isTimeControlEnabled: Boolean,
    maxTime: String,
    showError: Boolean,
    setShowError: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ConfigContent(navController, viewModel, alias, gridSize, isTimeControlEnabled, maxTime, showError, setShowError)
    }
}

@Composable
fun HorizontalConfigLayout(
    navController: NavController,
    viewModel: ConfigViewModel,
    alias: String,
    gridSize: Float,
    isTimeControlEnabled: Boolean,
    maxTime: String,
    showError: Boolean,
    setShowError: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConfigContent(navController, viewModel, alias, gridSize, isTimeControlEnabled, maxTime, showError, setShowError)
    }
}
@Composable
fun ConfigContent(
    navController: NavController,
    viewModel: ConfigViewModel,
    alias: String,
    gridSize: Float,
    isTimeControlEnabled: Boolean,
    maxTime: String,
    showError: Boolean,
    setShowError: (Boolean) -> Unit
) {
    TextField(
        value = alias,
        onValueChange = { viewModel.alias.value = it },
        label = { Text("Alias del jugador") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
    )

    Spacer(modifier = Modifier.height(20.dp))

    Text("Tamaño de la parrilla: ${gridSize.toInt()}x${gridSize.toInt()}", fontSize = 16.sp)
    Slider(
        value = gridSize,
        onValueChange = { viewModel.gridSize.value = it },
        valueRange = 3f..10f,
        steps = 7,
        modifier = Modifier.padding(vertical = 8.dp)
    )

    Spacer(modifier = Modifier.height(20.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isTimeControlEnabled,
            onCheckedChange = { viewModel.isTimeControlEnabled.value = it }
        )
        Text("Control del tiempo")
    }

    if (isTimeControlEnabled) {
        TextField(
            value = maxTime,
            onValueChange = {
                val isValid = viewModel.validateMaxTime(it)
                setShowError(!isValid)
                if (isValid) {
                    viewModel.maxTime.value = it
                }
            },
            label = { Text("Tiempo máximo (segundos)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = showError,
            modifier = Modifier.fillMaxWidth()
        )
        if (showError) {
            Text("Introduce un número válido de segundos (1-300).", color = MaterialTheme.colors.error)
        }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Button(
        onClick = {
            if (!isTimeControlEnabled || (!showError && maxTime.isNotEmpty())) {
                navController.navigate("gameScreen")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
    ) {
        Text("Iniciar Partida", fontSize = 18.sp)
    }
}


