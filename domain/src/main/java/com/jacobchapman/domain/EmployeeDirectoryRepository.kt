package com.jacobchapman.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface EmployeeDirectoryRepository {
    fun getEmployeeDirectory(): Flow<DomainData<List<Employee>, Unit>> {
        TODO("Not yet implemented")
    }

    fun refreshDirectory() {
        TODO("Not yet implemented")
    }
}
