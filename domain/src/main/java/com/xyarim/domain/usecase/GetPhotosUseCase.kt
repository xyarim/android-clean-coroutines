package com.xyarim.domain.usecase

import com.xyarim.domain.repository.PhotoRepository
import com.xyarim.domain.usecase.base.BaseUseCase
import com.xyarim.domain.usecase.base.Result

class GetPhotosUseCase(private val photoRepository: PhotoRepository) : BaseUseCase<Int>() {
    override suspend fun run(params: Int) {
        // Started loading
        resultChannel.send(Result.State.Loading)

        // Get person from persistence and send it, synchronous
        resultChannel.send(photoRepository.getPhotos(params))


        resultChannel.send(Result.State.Loaded)
    }
}