package com.xyarim.data.api.repository

import com.xyarim.data.api.service.PhotoService
import com.xyarim.domain.models.Photo
import com.xyarim.domain.repository.PhotoRepository
import com.xyarim.domain.usecase.base.Error
import com.xyarim.domain.usecase.base.Result

class PhotoRepositoryImpl(val photoService: PhotoService) : PhotoRepository {
    override suspend fun getPhotos(page: Int): Result<List<Photo>, Error> {
        val response = photoService.getPhotos(page = page).await()
        try {
            if (response.isSuccessful && response.body() != null) {
                return Result.Success(response.body()!!.map {
                    return@map Photo(it.id, url = it.imageUrls.raw)
                })
            } else {
                return Result.Failure(Error.ResponseError())
            }
        } catch (error: Exception) {
            return Result.Failure(Error.NetworkError())
        }
    }
}