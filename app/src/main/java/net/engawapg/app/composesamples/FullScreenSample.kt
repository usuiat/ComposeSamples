package net.engawapg.app.composesamples

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun FullScreenSample() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bgimage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "FullScreenSample",
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            Text(text = "TopCenter", modifier = Modifier.align(Alignment.TopCenter))
            Text(text = "TopStart", modifier = Modifier.align(Alignment.TopStart))
            Text(text = "TopEnd", modifier = Modifier.align(Alignment.TopEnd))
            Text(text = "CenterStart", modifier = Modifier.align(Alignment.CenterStart))
            Text(text = "CenterEnd", modifier = Modifier.align(Alignment.CenterEnd))
            Text(text = "BottomStart", modifier = Modifier.align(Alignment.BottomStart))
            Text(text = "BottomCenter", modifier = Modifier.align(Alignment.BottomCenter))
            Text(text = "BottomEnd", modifier = Modifier.align(Alignment.BottomEnd))
        }
    }

    val context = LocalContext.current
    DisposableEffect(true) {
        enableFullScreen(context)
        onDispose {
            disableFullScreen(context)
        }
    }
}

fun enableFullScreen(context: Context) {
    val window = context.findActivity().window

    WindowCompat.setDecorFitsSystemWindows(window, false)

    WindowInsetsControllerCompat(window, window.decorView).apply {
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        hide(WindowInsetsCompat.Type.systemBars())
    }
}

fun disableFullScreen(context: Context) {
    val window = context.findActivity().window

    WindowCompat.setDecorFitsSystemWindows(window, true)

    WindowInsetsControllerCompat(window, window.decorView).apply {
        show(WindowInsetsCompat.Type.systemBars())
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_TOUCH
    }
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}