package com.xyarim.domain.usecase.base

sealed class Error {

    class NetworkError() : Error()
    class GenericError() : Error()
    class ResponseError() : Error()
    class PersistenceError() : Error()
}