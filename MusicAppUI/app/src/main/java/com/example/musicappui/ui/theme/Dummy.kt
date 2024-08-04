package com.example.musicappui.ui.theme

import androidx.annotation.DrawableRes
import com.example.musicappui.R

data class Lib(@DrawableRes val icon: Int, val name: String)

val libraries = listOf<Lib>(
    Lib(R.drawable.ic_playlist_green,"PlayList"),
    Lib(R.drawable.ic_microphone,"Artist"),
    Lib(R.drawable.ic_baseline_album_24,"Album"),
    Lib(R.drawable.ic_baseline_music_note_24,"Songs"),
    Lib(R.drawable.ic_genre,"Genre"),

)