package com.example.animationsampe.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            AnimatedAsState()
            UpdateTransition()
        }
    }
}

// サイズと色をアニメーション付きで変更する方法1
// 単一の値をあアニメーションさせる
@Composable
fun AnimatedAsState() {
    var blue by remember { mutableStateOf(true) }

    //色をアニメーションつきで変更する
    val color by animateColorAsState(if (blue) Blue else Green, label = "")
    val size by animateDpAsState(if (blue) 150.dp else 300.dp, label = "")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { blue = !blue }) {
            Text(text = "Change Color With Animate")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .size(size)
                .background(color),
        )
    }
}


// サイズと色をアニメーション付きで変更する方法2
// 複数の値をまとめてアニメーションさせる
@Composable
fun UpdateTransition() {

    var boxState by remember {
        mutableStateOf(BoxState.Small)
    }

    val transition = updateTransition(targetState = boxState, label = "")
    val color by transition.animateColor(label = "") { state ->
        when (state) {
            BoxState.Small -> Green
            BoxState.Large -> Cyan
        }
    }

    val size by transition.animateDp(label = "") { state ->
        when (state) {
            BoxState.Small -> 150.dp
            BoxState.Large -> 300.dp
        }
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            boxState = when (boxState) {
                BoxState.Small -> BoxState.Large
                BoxState.Large -> BoxState.Small
            }
        }) {
            Text(text = "Change Color And Size With Anime")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .size(size)
                .background(color),
        )
    }
}

private enum class BoxState {
    Small,
    Large
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}