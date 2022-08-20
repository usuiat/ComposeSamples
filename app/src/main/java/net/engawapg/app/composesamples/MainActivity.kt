package net.engawapg.app.composesamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.engawapg.app.composesamples.ui.theme.ComposeSamplesTheme

val sampleList = listOf(
    "icons",
    "dialog",
    "lifecycleEvent",
    "flow",
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSamplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "samples") {
                        composable("samples") {
                            Samples { sample ->
                                navController.navigate(sample)
                            }
                        }
                        composable("icons") {
                            IconSample()
                        }
                        composable("dialog") {
                            DialogSample()
                        }
                        composable("lifecycleEvent") {
                            LifecycleEventSample()
                        }
                        composable("flow") {
                            FlowSample()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Samples(samples: List<String> = sampleList, onSelectSample: (String)->Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        items(samples) { sample ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { onSelectSample(sample) }
            ) {
                Text(
                    text = sample,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}
