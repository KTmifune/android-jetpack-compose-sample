package com.example.bottomnavigationsample.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CrossDadeDemo()
    }
}


private enum class DemoScene {
    Text, Icon
}

// 囲った内容をCrossFadeアニメーション
@Composable
fun CrossDadeDemo() {
    var scene by remember { mutableStateOf(DemoScene.Text) }

    Column {
        Button(
            onClick = {
                scene = when (scene) {
                    DemoScene.Text -> DemoScene.Icon
                    DemoScene.Icon -> DemoScene.Text
                }
            }
        ) {
            Text(text = "Change Scene")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Crossfade(targetState = scene, label = "") { scene ->
            when (scene) {
                DemoScene.Text -> {
                    Text(text = "Phone", fontSize = 30.sp)
                }

                DemoScene.Icon -> {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "",
                        modifier = Modifier.height(30.dp)
                    )
                }
            }
        }
    }
}