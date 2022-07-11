package com.jacobchapman.employeedirectory.utils

import androidx.compose.ui.graphics.Color
import com.jacobchapman.domain.Employee
import com.jacobchapman.domain.EmployeeType
import com.jacobchapman.employeedirectory.models.EmployeeViewData
import com.jacobchapman.employeedirectory.ui.theme.CoolBlue
import com.jacobchapman.employeedirectory.ui.theme.DewGreen
import com.jacobchapman.employeedirectory.ui.theme.FireRed

fun EmployeeType.toColor(): Color {
    return when (this) {
        EmployeeType.FULL_TIME -> CoolBlue
        EmployeeType.PART_TIME -> FireRed
        EmployeeType.CONTRACTOR -> DewGreen
        EmployeeType.UNKNOWN -> Color.Black
    }
}

fun Employee.toViewData() = EmployeeViewData(
    id = uuid,
    imageUrl = photoUrlSmall,
    type = employeeType.name,
    name = fullName,
    nameColor = employeeType.toColor(),
    summary = biography,
    team = team
)