package com.xyarim.randompics.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.xyarim.domain.models.Photo
import com.xyarim.domain.usecase.GetPhotosUseCase
import com.xyarim.domain.usecase.base.Error
import com.xyarim.domain.usecase.base.Result
import com.xyarim.randompics.base.BaseViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel

@ObsoleteCoroutinesApi
class PhotoListViewModel(private val getPhotosUseCase: GetPhotosUseCase) : BaseViewModel() {
    override val receiveChannel: ReceiveChannel<Result<Any, Error>>
        get() = getPhotosUseCase.receiveChannel

    private val _items = MutableLiveData<List<Photo>>().apply { value = emptyList() }
    val items: LiveData<List<Photo>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    var page: Int = 1

    override fun resolve(value: Result<Any, Error>) {
        value.handleResult(::handleSuccess, ::handleFailure, ::handleState)
    }

    fun requestItems() {
        getPhotosUseCase(page)
    }

    fun refresh() {
        _items.value = emptyList()
        page = 1
        requestItems()
    }

    fun handleSuccess(data: Any) {
        val oldItems = _items.value!!
        val items = oldItems + (data as List<Photo>)
        _items.postValue(items)
        page++

    }

    fun handleFailure(error: Error) {

    }

    fun handleState(state: Result.State) {
        _dataLoading.postValue(state is Result.State.Loading)
    }

}
