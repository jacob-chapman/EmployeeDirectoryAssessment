package com.jacobchapman.employeedirectory.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun <E> ErrorViewStateComponent(error: E?, actionHandler: ViewStateActionHandler) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Failed to Load View")
        Icon(Icons.Filled.Refresh, "Refresh", modifier = Modifier.clickable {
            actionHandler.retry()
        })
    }
}