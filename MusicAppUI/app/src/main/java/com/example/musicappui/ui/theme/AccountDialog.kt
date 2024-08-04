package com.example.musicappui.ui.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.primarySurface
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun AccountDialog(isShow: MutableState<Boolean>) {
    if (isShow.value) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(10.dp),
            shape = RoundedCornerShape(3.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            title = {
                Text(text = "Add Account")
            },
            text = {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        modifier = Modifier.padding(16.dp),
                        label = { Text(text = "Email") },
                        value = "",
                        onValueChange = {}
                    )

                    TextField(
                        modifier = Modifier.padding(16.dp),
                        label = { Text(text = "Password") },
                        value = "",
                        onValueChange = {}
                    )
                }
            },
            onDismissRequest = { isShow.value = false },
            confirmButton = {
                TextButton(
                    onClick = { isShow.value = false }
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { isShow.value = false }
                ) {
                    Text(text = "Dismiss")
                }

            }
        )
    }
}