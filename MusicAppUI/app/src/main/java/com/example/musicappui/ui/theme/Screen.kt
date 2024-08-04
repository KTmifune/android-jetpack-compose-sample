package com.example.musicappui.ui.theme

import androidx.annotation.DrawableRes
import com.example.musicappui.R


sealed class Screen(val title: String, val route: String) {

    sealed class BottomScreen(val bTitle: String, val bRoute: String, @DrawableRes val icon: Int) :
        Screen(bTitle, bRoute) {
        object Home : BottomScreen(
            "Home",
            "home",
            R.drawable.baseline_home_24
        )

        object Library: BottomScreen(
            "Library",
            "library",
            R.drawable.baseline_video_library_24
        )
        object Browse: BottomScreen(
            "Browse",
            "browse",
            R.drawable.baseline_apps_24
        )


    }

    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screen(dTitle, dRoute) {
        object Account : DrawerScreen(
            "Account",
            "account",
            R.drawable.baseline_account
        )

        object Subscription : DrawerScreen(
            "Subscription",
            "subscription",
            R.drawable.subscription
        )

        object AddAccount : DrawerScreen(
            "Add Account",
            "add_account",
            R.drawable.baseline_person_add_alt_1_24
        )

    }
}

val screenInsBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Library,
    Screen.BottomScreen.Browse,
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount,
)