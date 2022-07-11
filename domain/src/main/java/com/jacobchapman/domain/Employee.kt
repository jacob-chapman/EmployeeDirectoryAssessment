package com.jacobchapman.domain

data class Employee(
    val uuid: String = "",
    val fullName: String = "",
    val phoneNumber: String= "",
    val emailAddress: String= "",
    val biography: String= "",
    val photoUrlSmall: String= "",
    val photoUrlLarge: String= "",
    val team: String= "",
    val employeeType: EmployeeType = EmployeeType.UNKNOWN,
)

enum class EmployeeType {
    FULL_TIME,
    PART_TIME,
    CONTRACTOR,
    UNKNOWN;
}

