package com.jacobchapman.domain.usecase

import com.jacobchapman.domain.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

interface GetEmployeeDirectoryFiltersUseCase {
    operator fun invoke(): Flow<List<EmployeeFilter>>
}

@OptIn(ExperimentalCoroutinesApi::class)
@Singleton
class DefaultGetEmployeeDirectoryFiltersUseCase @Inject constructor(private val employeeDirectoryRepository: EmployeeDirectoryRepository) :
    GetEmployeeDirectoryFiltersUseCase {
    override fun invoke(): Flow<List<EmployeeFilter>> {
        return employeeDirectoryRepository.getEmployeeDirectory().mapLatest { domainData ->
            domainData.mapLoaded { employees ->
                employees.distinctBy { it.team }.map { employee ->
                    EmployeeFilter(employee.team) { filterEmployee ->
                        filterEmployee.team == employee.team
                    }
                }.plus(employeeTypeFilters())
            } ?: emptyList()
        }
    }

    private fun employeeTypeFilters() =
        EmployeeType.values().filter { it != EmployeeType.UNKNOWN }.map { employeeType ->
            EmployeeFilter(employeeType.prettifyName()) { employee ->
                employee.employeeType == employeeType
            }
        }
}

