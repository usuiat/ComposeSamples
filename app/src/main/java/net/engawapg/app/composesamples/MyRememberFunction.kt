package net.engawapg.app.composesamples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MyRememberFunctionSample() {
    val myState = rememberMyState()
    MyStateScreen(myState = myState)
}

@Composable
fun MyStateScreen(myState: MyState) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(myState.color)
            .fillMaxSize()
    ) {
        Button(
            onClick = myState::changeColor,
        ) {
            Text("Change Background Color")
        }
    }
}

@Composable
fun rememberMyState(): MyState {
    return remember {
        MyState()
    }
}

@Stable
class MyState {
    var color by mutableStateOf(Color.Red)
        private set

    fun changeColor() {
        color = when (color) {
            Color.Red -> Color.Green
            Color.Green -> Color.Blue
            else -> Color.Red
        }
    }
}