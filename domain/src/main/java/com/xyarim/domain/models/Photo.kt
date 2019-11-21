package com.xyarim.domain.models

/**
 * Class that represents a Photo in the domain layer.
 */
data class Photo(val id: String,
                 val url: String,
                 val color:String,
                 val width: Int,
                 val height: Int)
