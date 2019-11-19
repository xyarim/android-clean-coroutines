package com.xyarim.domain.usecase.base

sealed class Result<out T, out R> {
    class Success<out T>(val successData: T) : Result<T, Nothing>()
    class Failure<out R : Error>(val errorData: R) : Result<Nothing, R>()

    public sealed class State : Result<Nothing, Nothing>() {
        class Loading : State()
        class Loaded : State()
    }

    fun handleResult(
        successBlock: (T) -> Unit = {},
        failureBlock: (R) -> Unit = {},
        stateBlock: (State) -> Unit = {}
    ) {
        when (this) {
            is Success -> successBlock(successData)
            is Failure -> failureBlock(errorData)
            is State -> stateBlock(this)
        }
    }
}