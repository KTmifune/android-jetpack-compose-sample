package com.example.animationsampe.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun Anime5Screen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            TextScaleAnimation()
            Spacer(modifier = Modifier.height(32.dp))
            TextScaleAnimation2()
        }
    }
}

// 大きさを無限にアニメーション
@Composable
fun TextScaleAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "1")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = ""
    )
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color.Cyan,
        targetValue = Color.Red,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "color"
    )

    Box {
        Text(
            text = "Hello",
            color = animatedColor,
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    transformOrigin = TransformOrigin.Center
                )
                .align(Alignment.Center),
        )
    }
}

// 色と大きさを無限にアニメーション
@Composable
fun TextScaleAnimation2() {
    val infiniteTransition = rememberInfiniteTransition(label = "2")
    val scale by infiniteTransition.animateFloat(
        initialValue = 4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = ""
    )
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color(0xFF60DDAD),
        targetValue = Color(0xFF4285F4),
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "color"
    )

    Box {
        Text(
            text = "World",
            color = animatedColor,
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    transformOrigin = TransformOrigin.Center
                )
                .align(Alignment.Center),
        )
    }
}
