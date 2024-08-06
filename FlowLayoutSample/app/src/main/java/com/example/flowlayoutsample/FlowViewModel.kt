package com.example.flowlayoutsample

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class FlowViewModel : ViewModel() {
    private val _items = mutableStateListOf<String>()
    val items: List<String> = _items

    init {
        initItems()
    }

    fun initItems() {
        _items.clear()
        _items.addAll(defaultItemDate)
    }

    fun removeItem(item: String) {
        _items.remove(item)
    }

}


val defaultItemDate = mutableStateListOf(
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