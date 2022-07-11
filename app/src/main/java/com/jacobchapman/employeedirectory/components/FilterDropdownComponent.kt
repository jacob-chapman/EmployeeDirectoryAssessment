package com.jacobchapman.employeedirectory.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jacobchapman.employeedirectory.models.FilterViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface FilterDropdownHandler {
    fun filterSelected(filterViewData: FilterViewData)
    fun getFilters(): Flow<List<FilterViewData>>
}

@Composable
fun FilterDropdownComponent(
    actionHandler: FilterDropdownHandler
) {
    var expanded by remember { mutableStateOf(false) }
    val filters = if (expanded) {
        actionHandler.getFilters().collectAsState(initial = emptyList())
    } else {
        emptyFlow<List<FilterViewData>>().collectAsState(initial = emptyList())
    }
    Row(modifier = Modifier
        .requiredHeight(30.dp)
        .fillMaxWidth()
        .drawBehind {
            val strokeWidth = 0.5.dp.toPx()
            val y = size.height - strokeWidth / 2
            drawLine(
                color = Color.LightGray,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = strokeWidth
            )
        }) {
        Text("Filter", modifier = Modifier.clickable {
            expanded = !expanded
        })
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        filters.value.forEach { filter ->
            val backgroundColor = if (filter.isSelected) {
                Color.LightGray
            } else {
                Color.White
            }
            DropdownMenuItem(
                modifier = Modifier.background(backgroundColor),
                onClick = {
                    actionHandler.filterSelected(filter)
                    expanded = false
                }) {
                val itemName = if (filter.isSelected) {
                    "Active - ${filter.name}"
                } else {
                    filter.name
                }
                Text(itemName)
            }
        }
    }
}