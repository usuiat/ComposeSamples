package net.engawapg.app.composesamples

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.engawapg.app.composesamples.ui.theme.ComposeSamplesTheme

@Stable
data class Sample(
    val route: String,
    val titleResource: Int,
) {
    companion object {
        val MaterialIcons = Sample(
            route = "materialIcons",
            titleResource = R.string.title_material_icons,
        )
        val Dialog = Sample(
            route = "dialog",
            titleResource = R.string.title_dialog,
        )
        val LifecycleEvent = Sample(
            route = "lifecycleEvent",
            titleResource = R.string.title_lifecycle_event
        )
        val Flow = Sample(
            route = "flow",
            titleResource = R.string.title_flow
        )
        val StatusBarColorOnScroll = Sample(
            route = "statusBarColorOnScroll",
            titleResource = R.string.title_status_bar_on_scroll
        )
        val FullScreen = Sample(
            route = "fullScreen",
            titleResource = R.string.title_full_screen
        )
    }
}

val sampleList = listOf(
    Sample.MaterialIcons,
    Sample.Dialog,
    Sample.LifecycleEvent,
    Sample.Flow,
    Sample.StatusBarColorOnScroll,
    Sample.FullScreen,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* フルスクリーン時にカメラホールの周りにも描画するため */
        if (Build.VERSION.SDK_INT >= 28) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

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
                        composable(Sample.MaterialIcons.route) { IconSample() }
                        composable(Sample.Dialog.route) { DialogSample() }
                        composable(Sample.LifecycleEvent.route) { LifecycleEventSample() }
                        composable(Sample.Flow.route) { FlowSample() }
                        composable(Sample.StatusBarColorOnScroll.route) { StatusBarColorOnScrollSample() }
                        composable(Sample.FullScreen.route) { FullScreenSample() }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Samples(samples: List<Sample> = sampleList, onSelectSample: (String)->Unit) {
    LazyColumn {
        items(samples) { sample ->
            ListItem(
                headlineText = { Text(stringResource(id = sample.titleResource)) },
                modifier = Modifier.clickable { onSelectSample(sample.route) }
            )
            Divider()
        }
    }
}
