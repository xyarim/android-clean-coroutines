package com.xyarim.randompics.base

import androidx.lifecycle.ViewModel
import com.xyarim.domain.usecase.base.Result
import com.xyarim.domain.usecase.base.Error
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext
@ObsoleteCoroutinesApi
abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    protected abstract val receiveChannel: ReceiveChannel<Result<Any, Error>>

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    abstract fun resolve(value: Result<Any, Error>)

    init {
        processStream()
    }

    private fun processStream() {
        launch {
            receiveChannel.consumeEach {
                resolve(it)
            }
        }
    }

    override fun onCleared() {
        receiveChannel.cancel()
        coroutineContext.cancel()
        super.onCleared()
    }
}