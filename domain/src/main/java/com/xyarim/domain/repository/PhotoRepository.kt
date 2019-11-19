package com.xyarim.domain.repository

import com.xyarim.domain.models.Photo

/**
 * Interface that represents a Repository for getting [Photo] related data.
 */
interface PhotoRepository {
    /**
     * Get a List of [Photo].
     */
    suspend fun getPhotos(offset: Int): List<Photo>

}