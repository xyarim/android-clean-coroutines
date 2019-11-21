package com.xyarim.randompics.fragment

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.ViewPropertyTransition
import com.xyarim.domain.models.Photo
import com.xyarim.randompics.App
import com.xyarim.randompics.extensions.onLoadMore
import kotlinx.coroutines.ObsoleteCoroutinesApi


/**
 * [BindingAdapter]s for the [Photo]s list.
 */

@BindingAdapter("photo")
fun loadPhoto(imageView: ImageView, photo: Photo) {


    val metrics = App.instance.resources.displayMetrics
    val finalHeight = metrics.widthPixels / (photo.width.toFloat() / photo.height.toFloat())
    imageView.minimumHeight = finalHeight.toInt()


    val fadeAnimation = ViewPropertyTransition.Animator {
        val fadeAnim = ObjectAnimator.ofFloat(it, "alpha", 0f, 1f)
        fadeAnim.duration = 500
        fadeAnim.start()
    }


    Glide.with(imageView.context)
        .load(photo.url)
        .transition(GenericTransitionOptions.with(fadeAnimation))
        .into(imageView)

    val colorFrom = Color.WHITE
    val colorTo: Int = Color.parseColor(photo.color)

    val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
    colorAnimation.duration = 1000
    colorAnimation.addUpdateListener { animator -> imageView.setBackgroundColor(animator.animatedValue as Int) }
    colorAnimation.start()

}

@ObsoleteCoroutinesApi
@BindingAdapter("items", "viewmodel")
fun setItems(listView: RecyclerView, items: List<Photo>, viewModel: PhotoListViewModel) {
    (listView.adapter as PhotoAdapter).submitList(items)
    listView.onLoadMore { viewModel.requestItems() }
}
