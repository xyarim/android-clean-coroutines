package com.xyarim.domain.usecase.base

sealed class Error {

    object NetworkError : Error()
    object GenericError : Error()
    object ResponseError : Error()
    object PersistenceError : Error()
}