package com.xyarim.randompics.extensions

import androidx.recyclerview.widget.RecyclerView
import com.xyarim.randompics.utils.EndlessRecyclerOnScrollListener

fun RecyclerView.onLoadMore(block: () -> Unit) {
    this.clearOnScrollListeners()
    this.addOnScrollListener(object: EndlessRecyclerOnScrollListener(){
        override fun onLoadMore() {
            block.invoke()
        }

    })
}