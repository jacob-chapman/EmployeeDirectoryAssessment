package com.jacobchapman.network.models

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

enum class EmployeeTypeResponse {
    FULL_TIME,
    PART_TIME,
    CONTRACTOR,
    UNKNOWN;
}

class EmployeeTypeAdapter {
    @ToJson
    fun toJson(type: EmployeeTypeResponse): String = type.toString()

    @FromJson
    fun fromJson(value: String): EmployeeTypeResponse =
        EmployeeTypeResponse.values().firstOrNull { it.name.equals(value, true) } ?: EmployeeTypeResponse.UNKNOWN

}