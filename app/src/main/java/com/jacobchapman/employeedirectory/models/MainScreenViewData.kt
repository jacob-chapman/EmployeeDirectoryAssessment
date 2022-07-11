package com.jacobchapman.employeedirectory.models

import androidx.compose.ui.graphics.Color

sealed interface MainScreenViewData

data class EmployeeViewData(
    val id: String,
    val imageUrl: String,
    val type: String,
    val name: String,
    val nameColor: Color,
    val summary: String,
    val team: String
) : MainScreenViewData

data class LegendViewData(
    val items: List<LegendItemViewData>
) : MainScreenViewData