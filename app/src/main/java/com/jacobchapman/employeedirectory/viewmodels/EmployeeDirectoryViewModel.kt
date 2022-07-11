package com.jacobchapman.employeedirectory.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jacobchapman.domain.EmployeeType
import com.jacobchapman.domain.prettifyName
import com.jacobchapman.domain.usecase.GetEmployeeDirectoryFiltersUseCase
import com.jacobchapman.domain.usecase.GetEmployeeDirectoryUseCase
import com.jacobchapman.domain.usecase.RefreshEmployeeDirectoryUseCase
import com.jacobchapman.employeedirectory.models.*
import com.jacobchapman.employeedirectory.utils.toColor
import com.jacobchapman.employeedirectory.utils.toViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

typealias MainScreenViewState = ViewState<List<MainScreenViewData>, Unit>

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class EmployeeDirectoryViewModel @Inject constructor(
    getEmployeeDirectoryUseCase: GetEmployeeDirectoryUseCase,
    getEmployeeDirectoryFiltersUseCase: GetEmployeeDirectoryFiltersUseCase,
    private val refreshEmployeeDirectoryUseCase: RefreshEmployeeDirectoryUseCase
) :
    ViewModel() {

    private val _activeFilter = MutableStateFlow<FilterViewData?>(null)
    private val _initializeFilters = MutableSharedFlow<Unit>(replay = 0)

    val filterViewData: StateFlow<List<FilterViewData>> by lazy {
        combine(_activeFilter, getEmployeeDirectoryFiltersUseCase()) { activeFilter, filters ->
            filters.map { filter ->
                FilterViewData(
                    filter.name,
                    filter.name == activeFilter?.name,
                    filter.filterPredicate
                )
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }
    val directory: StateFlow<MainScreenViewState> =
        combine(_activeFilter, getEmployeeDirectoryUseCase()) { activeFilter, domainData ->
            val viewState = domainData.toViewState { employees ->
                (refreshing as MutableStateFlow).value = false
                val employeesViewData =
                    employees.filter(activeFilter?.predicate ?: { true }).map { employee ->
                        employee.toViewData()
                    }
                if (employeesViewData.isEmpty()) {
                    ViewState.Empty("No Employees Found.")
                } else {
                    ViewState.Loaded(employeesViewData.plus(
                        LegendViewData(
                            items = EmployeeType.values().filter { it != EmployeeType.UNKNOWN }
                                .map {
                                    LegendItemViewData(it.prettifyName(), it.toColor())
                                }
                        )
                    ))
                } as MainScreenViewState
            }
            viewState
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            ViewState.Loading as MainScreenViewState
        )
    val refreshing: StateFlow<Boolean> = MutableStateFlow(false)

    fun refresh() {
        (refreshing as MutableStateFlow).value = true
        refreshEmployeeDirectoryUseCase()
    }

    fun getFilters() {
        _initializeFilters.tryEmit(Unit)
    }

    fun filterSelected(filter: FilterViewData) {
        if (_activeFilter.value?.name == filter.name) {
            // clear out the filer
            _activeFilter.value = null
        } else {
            _activeFilter.value = filter
        }
    }
}

