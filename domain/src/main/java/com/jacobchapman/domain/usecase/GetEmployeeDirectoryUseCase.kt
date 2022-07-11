package com.jacobchapman.domain.usecase

import com.jacobchapman.domain.DomainData
import com.jacobchapman.domain.Employee
import com.jacobchapman.domain.EmployeeDirectoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface GetEmployeeDirectoryUseCase {
    operator fun invoke(): Flow<DomainData<List<Employee>, Unit>>
}

@Singleton
class DefaultGetEmployeeDirectoryUseCase @Inject constructor(private val employeeDirectoryRepository: EmployeeDirectoryRepository) :
    GetEmployeeDirectoryUseCase {
    override fun invoke(): Flow<DomainData<List<Employee>, Unit>> {
        return employeeDirectoryRepository.getEmployeeDirectory()
    }
}

