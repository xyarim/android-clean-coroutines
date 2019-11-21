package com.xyarim.randompics.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xyarim.domain.models.Photo
import com.xyarim.randompics.databinding.PhotoItemBinding

/**
 * Adapter for the photo list.
 */
class PhotoAdapter : ListAdapter<Photo, PhotoViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }
}

class PhotoViewHolder private constructor(private val binding: PhotoItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo) {
        binding.photo = photo
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): PhotoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = PhotoItemBinding.inflate(layoutInflater, parent, false)
            return PhotoViewHolder(binding)
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}