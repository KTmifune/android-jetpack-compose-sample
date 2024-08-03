package com.example.compose_pager_sample

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen() {

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            10
        })
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f)) // 上部のスペース
        HorizontalPager(
            contentPadding = PaddingValues(horizontal = 70.dp),
            state = pagerState,
        ) { pageIndex ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                val pageOffset = (
                        (pagerState.currentPage - pageIndex) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue

                // 最小の高さを 200dp に設定
                val minHeight = 200.dp
                // 高さの差 (300dp - 200dp)
                val heightDifference = 100.dp

                val height = minHeight + heightDifference * (1 - pageOffset.coerceIn(0f, 1f))

                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(height)
                        .graphicsLayer {
                            // We animate the alpha, between 50% and 100%
                            alpha = lerp(
                                start = 0.1f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .border(
                            BorderStroke(4.dp, Color.Black),
                            RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    CoilImage(index = pageIndex)
                }
            }

        }

        // ボタンでページ操作スキップボタン
        Row {
            val coroutineScope = rememberCoroutineScope()
            val iconModifier = Modifier.size(50.dp)

            IconButton(
                onClick = {
                    coroutineScope.launch {
                        // 最初のページに移動
                        pagerState.animateScrollToPage(0)
                    }
                }) {
                Icon(
                    modifier = iconModifier,
                    painter = painterResource(id = R.drawable.icon_skip_previous),
                    contentDescription = "",
                )
            }
            IconButton(onClick = {
                coroutineScope.launch {
                    // 前のページに移動
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }) {
                Icon(
                    modifier = iconModifier,
                    painter = painterResource(id = R.drawable.icon_navigate_before),
                    contentDescription = ""
                )
            }
            IconButton(onClick = {
                coroutineScope.launch {
                    // 次のページに移動
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }) {
                Icon(
                    modifier = iconModifier,
                    painter = painterResource(id = R.drawable.icon_navigate_next),
                    contentDescription = ""
                )
            }
            IconButton(onClick = {
                coroutineScope.launch {
                    // 最後のページに移動
                    pagerState.animateScrollToPage(pagerState.pageCount - 1)
                }
            }) {
                Icon(
                    modifier = iconModifier,
                    painter = painterResource(id = R.drawable.icon_skip_next),
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f)) // 下部のスペース
    }
}

@Composable
fun CoilImage(index: Int) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrlData[index])
            .crossfade(true)
            .build(),
        contentDescription = "",
    )
}

@Preview(showBackground = true)
@Composable
fun PagerScreenPreview() {
    PagerScreen()
}

val imageUrlData = listOf(
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png",
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png",
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/200.png",
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/250.png",
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/300.png",
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/350.png",
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/610.png",
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/620.png",
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/630.png",
)