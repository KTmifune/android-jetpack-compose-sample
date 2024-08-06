package com.example.flowlayoutsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowScreen(innerPadding: PaddingValues = PaddingValues(0.dp)) {

    val chipItems = remember {
        mutableStateListOf(
            "Price: High to Low",
            "Avg rating: 4+",
            "Free breakfast",
            "Free cancellation",
            "£50 pn",
            "£50 pn",
            "£50 pn",
            "Free breakfast",
            "Free cancellation",
            "£50 pn",
            "£50 pn",
            "Free breakfast",
            "£50 pn"
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        FlowRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            chipItems.forEachIndexed { index, it ->
                ChipItem(
                    index = index,
                    text = it,
                    onClick = {
                        chipItems.remove(it)
                    }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipItem(index: Int = 0, text: String, onClick: () -> Unit) {
    InputChip(
        selected = false,
        onClick = onClick,
        label = {
            Text(text = "$index : $text")
        }
    )

}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FlowScreenPreview() {
    FlowScreen()
}

@Preview(showBackground = true)
@Composable
fun AssistChipPreview() {
    AssistChip(
        onClick = {},
        label = {
            Text(text = "test")
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun InputChipPreview() {
    InputChip(
        selected = true,
        onClick = {},
        label = {
            Text(text = "test")
        }
    )
}