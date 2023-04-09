package net.engawapg.app.composesamples

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import kotlin.math.*

@Composable
fun TimeBaseAnimSample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ) {
        val modifier = Modifier
            .weight(1f)
            .fillMaxSize()
            .border(1.dp, Color.Black)
        UniformLinearMotion(modifier)
        ParabolicMotion(modifier)
        WaveMotion(modifier)
    }
}

@Composable
fun UniformLinearMotion(
    modifier: Modifier = Modifier,
) {
    var timeSec by remember { mutableStateOf(0f) }
    LaunchedEffect(true) {
        while (true) {
            timeSec = withFrameMillis { it / 1000f }
        }
    }
    Canvas(modifier = modifier) {
        val radius = 50f
        val speed = Offset(500f, 300f)
        // 円の中心が移動できる領域の大きさ
        val validSize = Size(size.width - radius * 2, size.height - radius * 2)
        // 時刻 timeSec におけるx, y座標を計算。端まで到達したら折り返す。
        val xTmp = (timeSec * speed.x) % (validSize.width * 2)
        val x = radius + if (xTmp > validSize.width) validSize.width * 2 - xTmp else xTmp
        val yTmp = (timeSec * speed.y) % (validSize.height * 2)
        val y = radius + if (yTmp > validSize.height) validSize.height * 2 - yTmp else yTmp
        drawCircle(
            color = Color.Red,
            radius = radius,
            center = Offset(x, y)
        )
    }
}

@Composable
fun ParabolicMotion(
    modifier: Modifier = Modifier,
) {
var timeSec by remember { mutableStateOf(0f) }
LaunchedEffect(true) {
    while (true) {
        timeSec = withFrameMillis { it / 1000f }
    }
}
    Canvas(modifier = modifier) {
        val radius = 50f
        val xSpeed = 500f
        val yAcceleration = 2000f
        // 円の中心が移動できる領域の大きさ
        val validSize = Size(size.width - radius * 2, size.height - radius * 2)
        // 時刻 timeSec におけるx, y座標を計算。端まで到達したら折り返す。
        val xTmp = (timeSec * xSpeed) % (validSize.width * 2)
        val x = radius + if (xTmp > validSize.width) validSize.width * 2 - xTmp else xTmp
        // 画面の上から下まで到達する時間
        val period = sqrt(validSize.height / yAcceleration)
        // timeSecを -period ～ period の範囲に正規化
        val timeForY = (timeSec % (period * 2)) - period
        val y = radius + yAcceleration * timeForY * timeForY
        drawCircle(
            color = Color.Blue,
            radius = radius,
            center = Offset(x, y)
        )
    }
}

@Composable
fun WaveMotion(
    modifier: Modifier = Modifier,
) {
    var timeSec by remember { mutableStateOf(0f) }
    LaunchedEffect(true) {
        while (true) {
            timeSec = withFrameMillis { it / 1000f }
        }
    }
    Canvas(modifier = modifier) {
        val nPoints = 40
        val amp = 100f
        val omega = 6f
        // sin波を描画するための点のリスト
        val points = List(nPoints) { i ->
            Offset(
                x = size.width * i / (nPoints - 1),
                y = size.height / 2 + amp * sin(omega * (i + timeSec))
            )
        }
        // sin波より下の部分を塗りつぶす
        val path = Path().apply {
            moveTo(points.first().x, points.first().y)
            points.forEach { lineTo(it.x, it.y) }
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        drawPath(
            color = Color.Cyan,
            path = path,
        )
    }
}
