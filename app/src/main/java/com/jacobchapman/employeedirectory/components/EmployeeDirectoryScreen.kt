package com.jacobchapman.employeedirectory.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jacobchapman.employeedirectory.models.EmployeeViewData
import com.jacobchapman.employeedirectory.models.FilterViewData
import com.jacobchapman.employeedirectory.models.LegendViewData
import com.jacobchapman.employeedirectory.viewmodels.EmployeeDirectoryViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EmployeeDirectoryScreen(viewModel: EmployeeDirectoryViewModel) {
    val directoryState = viewModel.directory.collectAsState()
    val refreshing by viewModel.refreshing.collectAsState()
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ViewStateComponent(
            viewState = directoryState.value,
            object : ViewStateActionHandler {
                override fun retry() {
                    viewModel.refresh()
                }
            }) { mainScreenViewData ->
            Column(modifier = Modifier.fillMaxSize()) {
                FilterDropdownComponent(
                    actionHandler = object : FilterDropdownHandler {
                        override fun filterSelected(filterViewData: FilterViewData) {
                            viewModel.filterSelected(filterViewData)
                        }

                        override fun getFilters(): Flow<List<FilterViewData>> {
                            return viewModel.filterViewData
                        }
                    })
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = refreshing),
                    onRefresh = {
                        viewModel.refresh()
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn {
                        items(mainScreenViewData) { viewData ->
                            when (viewData) {
                                is EmployeeViewData -> EmployeeComponent(viewData = viewData)
                                is LegendViewData -> LegendComponent(viewData = viewData)
                            }
                        }
                    }
                }
            }
        }
    }
}