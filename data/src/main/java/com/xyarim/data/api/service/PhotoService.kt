package com.xyarim.data.api.service

import com.xyarim.data.api.response.PhotoResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("/photos")
    suspend fun getPhotos(@Query("page") page: Int): Deferred<Response<List<PhotoResponse>>>
}