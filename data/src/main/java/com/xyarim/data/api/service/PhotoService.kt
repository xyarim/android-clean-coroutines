package com.xyarim.data.api.service

import com.xyarim.data.api.response.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("/photos?client_id=e2658d4b6b17ae24b50a7ab36d13ca67da9761322a5e4cb0e9cc531e69cecb90")
    suspend fun getPhotos(@Query("page") page: Int): Response<List<PhotoResponse>>
}