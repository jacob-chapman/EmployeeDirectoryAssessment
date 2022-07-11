package com.jacobchapman.employeedirectory.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jacobchapman.employeedirectory.models.ViewState

@Composable
fun <T, E> ViewStateComponent(
    viewState: ViewState<T, E>,
    actionHandler: ViewStateActionHandler,
    loadedComponent: @Composable (T) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (viewState) {
            is ViewState.Empty -> EmptyViewStateComponent(message = viewState.message,actionHandler = actionHandler)
            is ViewState.Error -> ErrorViewStateComponent(
                error = viewState.errorViewData,
                actionHandler = actionHandler
            )
            is ViewState.Loaded -> loadedComponent(viewState.viewData)
            ViewState.Loading -> CircularProgressIndicator()
        }
    }
}

