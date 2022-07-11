package com.jacobchapman.employeedirectory.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jacobchapman.employeedirectory.models.LegendViewData


@Composable
fun LegendComponent(viewData: LegendViewData) {
    Column() {
        Text("Legend", modifier = Modifier
            .drawBehind {
                val strokeWidth = 0.5.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
            .padding(bottom = 4.dp))
        viewData.items.forEach {
            Text(it.title, color = it.color)
        }
    }
}