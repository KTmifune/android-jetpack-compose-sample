package com.example.animationsampe.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun Anime4Screen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            AnimateContentDemo()
            Spacer(modifier = Modifier.height(20.dp))
            MovedContentDemo()
        }
    }
}

// 囲った内容をCrossFadeアニメーション
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateContentDemo() {
    Column {
        var count by remember { mutableIntStateOf(0) }
        AnimatedContent(
            modifier = Modifier.background(White),
            targetState = count,
            label = "",
            transitionSpec = {
                // アニメーションの動きを指定。デフォルトはフェードスルー
                // 入力された数値と前の数値を比較
                if (targetState > initialState) {
                    // カウント増加時のアニメーション
                    // 新しい数値は画面外から上にスライドしながらフェードインする
                    // 初期の数値は画面内から上にスライドしながらフェードアウトする。
                    // この2つのアニメーションを同時に実行する
                    (slideInVertically { height -> height } + fadeIn()).togetherWith(
                        slideOutVertically { height -> - height } + fadeOut())
                } else {
                    // カウント減少時のアニメーション
                    // 新しい数値は画面外から下にスライドしながらフェードインする
                    // 初期の数値は画面内から下にスライドしながらフェードアウトする
                    (slideInVertically { height -> - height } + fadeIn()).togetherWith(
                        slideOutVertically { height -> height } + fadeOut())
                }.using(
                    // アニメーション中に適応する追加の変換
                    // アニメーション中に要素のサイズが変化する場合でも、クリッピングを行わず、
                    // 要素全体を表示するようにします。
                    // これにより、スライドイン/アウトするテキストが画面外にはみ出して表示されるようになります。
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Box(
                modifier = Modifier.size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$targetCount",
                    fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            IconButton(onClick = { count ++ }) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Add")
            }
            IconButton(onClick = { count -- }) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Minus")
            }
        }
    }

}


// タップしたら移動するボックス
@Composable
fun MovedContentDemo() {
    var moved by remember { mutableStateOf(false) }

    // ピクセル値で移動距離を保持
    val pxToMove = with(LocalDensity.current) {
        100.dp.toPx().roundToInt()
    }

    // アニメーション状態を保持する
    val offset by animateIntOffsetAsState(
        targetValue = if (moved) {
            // 右にpxToMove 下にpxToMove移動
            IntOffset(pxToMove, pxToMove)
        } else {
            IntOffset.Zero
        },
        label = "offset"
    )

    Box(
        modifier = Modifier
            .offset {
                offset
            }
            .background(Blue)
            .size(100.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                moved = !moved
            }
    )
}