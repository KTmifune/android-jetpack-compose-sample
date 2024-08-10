package com.example.animationsampe.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun TopScreen() {
    val items = listOf(
        Screen.Anime1, Screen.Anime2, Screen.Anime3, Screen.Anime4, Screen.Anime5
    )
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(backgroundColor = Color.Yellow.copy(alpha = 0.1f),
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                items = items,
                navController = navController,
            )
        }) { innerPadding ->
        NavHost(
            navController, startDestination = Screen.Anime1.route, Modifier.padding(innerPadding)
        ) {
            composable(Screen.Anime1.route) {
                Anime1Screen()
            }
            composable(Screen.Anime2.route) {
                Anime2Screen()
            }
            composable(Screen.Anime3.route) {
                Anime3Screen()
            }

            composable(Screen.Anime4.route) {}

            composable(Screen.Anime5.route) {}
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<Screen>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
            .clip(RoundedCornerShape(25.dp)), backgroundColor = Color.White, elevation = 10.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            // 選択状態かどうか
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            BottomNavigationItem(selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        // 常にNavigationGraphの開始地点までバックスタックをクリアし
                        //異なるタブを連続でタップするとバックスタックに画面が積み重なる減少を防ぐ
                        // 現在から最初の画面の間のバックスタックを削除（最初の1つ以外削除）
                        popUpTo(navController.graph.findStartDestination().id) {
                            // 削除される画面の状態を保持
                            // 再度その画面に遷移したときに以前の状態を復元できる
                            saveState = true
                        }
                        //  既にバックスタックのトップに存在する画面に遷移しようとした場合、新しいインスタンスを作成せず、既存のインスタンスを再利用
                        launchSingleTop = true
                        // 再表示される画面が以前の状態を保持している場合、その状態を復元。saveState = trueのときのみ有効
                        restoreState = true
                    }
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                label = {
                    Text(
                        text = screen.name,
                        fontSize = 14.sp,
                        color = if (selected) Color.Black else Color.Gray
                    )
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = if (selected) screen.selectedIcon else screen.unselectedIcon,
                            contentDescription = screen.name
                        )
                    }
                })
        }
    }
}

sealed class Screen(
    val route: String,
    val name: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {

    data object Anime1 : Screen("1", "Anime1", Icons.Filled.Face, Icons.Outlined.Face)

    data object Anime2 : Screen("2", "Anime2", Icons.Filled.Face, Icons.Outlined.Face)

    data object Anime3 : Screen("3", "Anime3", Icons.Filled.Face, Icons.Outlined.Face)

    data object Anime4 : Screen("4", "Anime4", Icons.Filled.Face, Icons.Outlined.Face)

    data object Anime5 : Screen("5", "Anime5", Icons.Filled.Face, Icons.Outlined.Face)
}

@Preview(backgroundColor = 0xBEBEBE, showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    TopScreen()
}