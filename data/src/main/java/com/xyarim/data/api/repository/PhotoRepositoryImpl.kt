package com.xyarim.data.api.repository

import com.xyarim.data.api.service.PhotoService
import com.xyarim.domain.models.Photo
import com.xyarim.domain.repository.PhotoRepository
import com.xyarim.domain.usecase.base.Error
import com.xyarim.domain.usecase.base.Result

class PhotoRepositoryImpl(val photoService: PhotoService) : PhotoRepository {
    override suspend fun getPhotos(page: Int): Result<List<Photo>, Error> {
        try {
            val response = photoService.getPhotos(page = page)

            if (response.isSuccessful && response.body() != null) {
                return Result.Success(response.body()!!.map {
                    return@map Photo(
                        it.id,
                        url = it.imageUrls.full,
                        color = it.color,
                        width = it.width,
                        height = it.height
                    )
                })
            } else {
                return Result.Failure(Error.ResponseError)
            }
        } catch (error: Exception) {
            return Result.Failure(Error.NetworkError)
        }
    }
}