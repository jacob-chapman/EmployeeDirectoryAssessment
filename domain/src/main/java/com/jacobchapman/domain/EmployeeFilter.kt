package com.jacobchapman.domain

data class EmployeeFilter(
    val name: String,
    val filterPredicate: (Employee) -> Boolean
)

fun EmployeeType.prettifyName() = name.replace("_", " ").lowercase().capitalize()