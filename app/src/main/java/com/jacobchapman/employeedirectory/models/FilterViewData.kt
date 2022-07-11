package com.jacobchapman.employeedirectory.models

import com.jacobchapman.domain.Employee

data class FilterViewData(
    val name: String,
    val isSelected: Boolean,
    val predicate: (Employee) -> Boolean
)