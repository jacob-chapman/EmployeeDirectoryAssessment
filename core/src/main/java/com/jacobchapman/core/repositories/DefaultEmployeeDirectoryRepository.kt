package com.jacobchapman.core.repositories

import com.jacobchapman.core.DomainDataManager
import com.jacobchapman.domain.DomainData
import com.jacobchapman.domain.Employee
import com.jacobchapman.domain.EmployeeDirectoryRepository
import com.jacobchapman.domain.EmployeeType
import com.jacobchapman.network.api.EmployeeDirectoryApi
import com.jacobchapman.network.models.EmployeeDirectoryResponse
import com.jacobchapman.network.models.EmployeeResponse
import com.jacobchapman.network.models.EmployeeTypeResponse
import com.jacobchapman.network.utils.toDomainData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultEmployeeDirectoryRepository @Inject constructor(private val employeeDirectoryApi: EmployeeDirectoryApi) :
    EmployeeDirectoryRepository {

    private val directoryDataManager = DomainDataManager {
        employeeDirectoryApi.getEmployees().toDomainData(
            onError = { Unit },
            validateSuccess = ::validateEmployeeResponse
        ) { response ->
            println("CALLING")
            response.employees.orEmpty().map { it.toDomainData() }
        }
    }


    override fun getEmployeeDirectory(): Flow<DomainData<List<Employee>, Unit>> {
        return directoryDataManager.observe()
    }

    override fun refreshDirectory() {
        directoryDataManager.refresh()
    }

    private fun validateEmployeeResponse(employeeDirectoryResponse: EmployeeDirectoryResponse): Boolean {
        return employeeDirectoryResponse.employees?.let { employees ->
            employees.any { employeeResponse ->
                !employeeResponse.uuid.isNullOrEmpty()
                        && !employeeResponse.fullName.isNullOrEmpty()
                        && !employeeResponse.emailAddress.isNullOrEmpty()
                        && !employeeResponse.team.isNullOrEmpty()
                        && employeeResponse.employeeType != EmployeeTypeResponse.UNKNOWN
            }
        } ?: false
    }

    private fun EmployeeResponse.toDomainData(): Employee =
        Employee(
            uuid = uuid.orEmpty(),
            fullName = fullName.orEmpty(),
            phoneNumber = phoneNumber.orEmpty(),
            emailAddress = emailAddress.orEmpty(),
            biography = biography.orEmpty(),
            photoUrlSmall = photoUrlSmall.orEmpty(),
            photoUrlLarge = photoUrlLarge.orEmpty(),
            team = team.orEmpty(),
            employeeType = when (employeeType) {
                EmployeeTypeResponse.FULL_TIME -> EmployeeType.FULL_TIME
                EmployeeTypeResponse.PART_TIME -> EmployeeType.PART_TIME
                EmployeeTypeResponse.CONTRACTOR -> EmployeeType.CONTRACTOR
                EmployeeTypeResponse.UNKNOWN -> EmployeeType.UNKNOWN
                null -> EmployeeType.UNKNOWN
            }
        )
}