package com.biondic.rssreader.core.model

sealed class RepositoryError : AppError()

sealed class NetworkError : RepositoryError() {
    data class UnknownNetworkError(val message: String) : NetworkError()
    data object SerializationError : NetworkError()
}

sealed class DatabaseError : RepositoryError() {
    data object UnknownDatabaseError : DatabaseError()
    data class DatabaseOperationError(val message: String) : DatabaseError()
}
