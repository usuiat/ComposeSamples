package net.engawapg.app.composesamples

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.toSize
import java.lang.Float.max

@Stable
class ZoomState(private val maxScale: Float) {
    private var _scale = mutableStateOf(1f)
    val scale: Float
        get() = _scale.value

    private var _offsetX = mutableStateOf(0f)
    val offsetX: Float
        get() = _offsetX.value

    private var _offsetY = mutableStateOf(0f)
    val offsetY: Float
        get() = _offsetY.value

    private var layoutSize = Size.Zero
    fun setLayoutSize(size: Size) {
        layoutSize = size
        updateFitImageSize()
    }

    private var imageSize = Size.Zero
    fun setImageSize(size: Size) {
        imageSize = size
        updateFitImageSize()
    }

    private var fitImageSize = Size.Zero
    private fun updateFitImageSize() {
        if ((imageSize == Size.Zero) || (layoutSize == Size.Zero)) {
            fitImageSize = Size.Zero
            return
        }

        val imageAspectRatio = imageSize.width / imageSize.height
        val layoutAspectRatio = layoutSize.width / layoutSize.height

        fitImageSize = if (imageAspectRatio > layoutAspectRatio) {
            imageSize * (layoutSize.width / imageSize.width)
        } else {
            imageSize * (layoutSize.height / imageSize.height)
        }
    }

    fun applyGesture(pan: Offset, zoom: Float) {
        _scale.value = (_scale.value * zoom).coerceIn(1f, maxScale)

        val boundX = max((fitImageSize.width * _scale.value - layoutSize.width), 0f) / 2f
        _offsetX.value = (_offsetX.value + pan.x).coerceIn(-boundX, boundX)

        val boundY = max((fitImageSize.height * _scale.value - layoutSize.height), 0f) / 2f
        _offsetY.value = (_offsetY.value + pan.y).coerceIn(-boundY, boundY)
    }
}

@Composable
fun rememberZoomState(maxScale: Float) = remember { ZoomState(maxScale) }

@Composable
fun ZoomImageSample() {
    val painter = painterResource(id = R.drawable.bird)
    val zoomState = rememberZoomState(maxScale = 5f)
    zoomState.setImageSize(painter.intrinsicSize)
    Image(
        painter = painter,
        contentDescription = "Zoomable bird image",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { size ->
                zoomState.setLayoutSize(size.toSize())
            }
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    zoomState.applyGesture(pan = pan, zoom = zoom)
                }
            }
            .graphicsLayer {
                scaleX = zoomState.scale
                scaleY = zoomState.scale
                translationX = zoomState.offsetX
                translationY = zoomState.offsetY
            }
    )
}