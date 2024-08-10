package com.example.bottomnavigationsample.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavoriteScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            AnimatedVisibilityDemo()
            AnimateContentSizeDemo()
        }
    }
}

// 囲った内容をアニメーションで出し入れする
@Composable
fun AnimatedVisibilityDemo() {
    var visible by remember { mutableStateOf(true) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { visible = !visible }) {
            Text(if (visible) "Hide" else "SHOW")
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideIn(initialOffset = { IntOffset(0, 100) }),
            exit = fadeOut() +  slideOut(targetOffset = { IntOffset(0, 100) })
        ) {
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .background(Red)
            )
        }
    }
}

// animateContentSize()でコンテンツサイズを自動アニメーション
@Composable
fun AnimateContentSizeDemo() {
    var expanded by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { expanded = !expanded }) {
            Text(if (expanded) "Shrink" else "Expand")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .background(LightGray)
                .animateContentSize()
        ) {
            Text(
                text = "Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!,Hello, World!",
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Justify,
                maxLines = if (expanded) Int.MAX_VALUE else 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen()
}
