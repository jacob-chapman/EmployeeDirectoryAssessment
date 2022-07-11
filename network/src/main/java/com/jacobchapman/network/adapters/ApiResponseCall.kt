package com.jacobchapman.network.adapters

import com.jacobchapman.network.models.ApiResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResponseCall<T : Any, E : Any>(
    private val proxy: Call<T>
) : Call<ApiResponse<T, E>> {

    override fun enqueue(callback: Callback<ApiResponse<T, E>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val code = response.code()
                val errorBody = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@ApiResponseCall,
                            Response.success(ApiResponse.Success(body))
                        )
                    } else {
                        callback.onResponse(
                            this@ApiResponseCall,
                            Response.success(ApiResponse.ServerError(null, code))
                        )
                    }
                } else {
                    // todo convert error body
                    callback.onResponse(
                        this@ApiResponseCall,
                        Response.success(ApiResponse.ServerError(null, code))
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(ApiResponse.NetworkError(t))
                )
            }
        })
    }


    override fun clone(): Call<ApiResponse<T, E>> =
        ApiResponseCall<T, E>(proxy.clone())


    override fun execute(): Response<ApiResponse<T, E>> =
        throw UnsupportedOperationException("Api Response call does not support synchronous execution")

    override fun isExecuted(): Boolean = proxy.isExecuted

    override fun cancel() = proxy.cancel()

    override fun isCanceled(): Boolean = proxy.isCanceled

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()

}