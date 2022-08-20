package net.engawapg.app.composesamples

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogSample() {
    var showDialog by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("Result") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.padding(50.dp)
        ) {
            Text("Show Dialog")
        }
        Text(
            text = result
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                result = "Dismiss"
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        result = "OK"
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        result = "Cancel"
                        showDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            },
            title = {
                Text("AlertDialog")
            },
            text = {
                Text("これはJetpack Composeのダイアログです。Material3デザインに対応しています。")
            },
        )
    }
}