package com.jacobchapman.network.models

import com.squareup.moshi.Json

data class EmployeeResponse(
    val uuid: String? = null,
    @Json(name = "full_name") val fullName: String? = null,
    @Json(name = "phone_number") val phoneNumber: String? = null,
    @Json(name = "email_address") val emailAddress: String? = null,
    val biography: String? = null,
    @Json(name = "photo_url_small") val photoUrlSmall: String? = null,
    @Json(name = "photo_url_large") val photoUrlLarge: String? = null,
    val team: String? = null,
    @Json(name = "employee_type") val employeeType: EmployeeTypeResponse? = null,
)