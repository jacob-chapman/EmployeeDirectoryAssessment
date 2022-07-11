package com.jacobchapman.domain.usecase

import com.jacobchapman.domain.EmployeeDirectoryRepository
import javax.inject.Inject
import javax.inject.Singleton

fun interface RefreshEmployeeDirectoryUseCase {
    operator fun invoke()
}

@Singleton
class DefaultRefreshEmployeeDirectoryUseCase @Inject constructor(private val employeeDirectoryRepository: EmployeeDirectoryRepository) :
    RefreshEmployeeDirectoryUseCase {
    override fun invoke() {
        employeeDirectoryRepository.refreshDirectory()
    }
}
