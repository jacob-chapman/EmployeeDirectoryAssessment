package com.jacobchapman.domain

import app.cash.turbine.test
import com.jacobchapman.domain.usecase.DefaultGetEmployeeDirectoryFiltersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class GetEmployeeDirectoryFiltersTest {
    @Test
    fun ensure_all_teams_to_filter() = runTest {
        val useCase =
            DefaultGetEmployeeDirectoryFiltersUseCase(object : EmployeeDirectoryRepository {
                override fun getEmployeeDirectory(): Flow<DomainData<List<Employee>, Unit>> {
                    return flowOf(
                        DomainData.Loaded(
                            listOf(
                                Employee(team = "data"),
                                Employee(team = "product"),
                                Employee(team = "data"),
                                Employee(team = "product")
                            )
                        )
                    )
                }
            })

        val response = useCase()
        response.test {
            val result = awaitItem()
            assertNotNull(result)
            assertEquals(2 + 3, result.size)
            cancelAndIgnoreRemainingEvents()
        }
    }
}