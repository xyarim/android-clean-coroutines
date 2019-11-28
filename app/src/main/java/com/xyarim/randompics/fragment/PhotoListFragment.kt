package com.xyarim.randompics.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xyarim.randompics.databinding.PhotoFragmentBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.ext.android.inject

/**
 * Display a list of [Photo]s.
 */

@ObsoleteCoroutinesApi
class PhotoListFragment : Fragment() {

    private val viewModel: PhotoListViewModel by inject()

    private lateinit var photoFragmentBinding: PhotoFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        photoFragmentBinding = PhotoFragmentBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
            }
        // Set the lifecycle owner to the lifecycle of the view
        photoFragmentBinding.lifecycleOwner = this.viewLifecycleOwner
        return photoFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        photoFragmentBinding.photoList.layoutManager =
            LinearLayoutManager(this@PhotoListFragment.context)
        photoFragmentBinding.photoList.adapter = PhotoAdapter()
        viewModel.requestItems()
    }

}
