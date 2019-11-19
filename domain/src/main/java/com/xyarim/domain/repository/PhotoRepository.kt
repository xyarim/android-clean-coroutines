package com.xyarim.domain.repository

import com.xyarim.domain.models.Photo
import com.xyarim.domain.usecase.base.Error
import com.xyarim.domain.usecase.base.Result

/**
 * Interface that represents a Repository for getting [Photo] related data.
 */
interface PhotoRepository {
    /**
     * Get a List of [Photo].
     */
    suspend fun getPhotos(page: Int): Result<List<Photo>, Error>

}