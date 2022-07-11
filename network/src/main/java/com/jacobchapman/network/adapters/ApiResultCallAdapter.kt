package com.jacobchapman.network.adapters

import com.jacobchapman.network.models.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ApiResultCallAdapter<T : Any, E : Any>(
    private val resultType: Type
) : CallAdapter<T, Call<ApiResponse<T, E>>> {
    override fun responseType(): Type = resultType
    override fun adapt(call: Call<T>): Call<ApiResponse<T, E>> {
        return ApiResponseCall(call)
    }
}