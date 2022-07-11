package com.jacobchapman.network.api

import com.jacobchapman.network.models.ApiResponse
import com.jacobchapman.network.models.EmployeeDirectoryResponse
import retrofit2.http.GET


interface EmployeeDirectoryApi {

    @GET("employees.json")
    suspend fun getEmployees(): ApiResponse<EmployeeDirectoryResponse, Unit>
}


