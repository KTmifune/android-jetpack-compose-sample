package com.example.flowlayoutsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowScreen(innerPadding: PaddingValues = PaddingValues(0.dp)) {

    val viewModel: FlowViewModel = viewModel()
    val chipItems = viewModel.items

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, bottom = 30.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
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
                            viewModel.removeItem(it)
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { viewModel.initItems() }) {
                Text("RESET")
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