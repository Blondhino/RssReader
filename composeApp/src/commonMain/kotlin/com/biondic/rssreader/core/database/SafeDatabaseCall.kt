package com.biondic.rssreader.core.database

import arrow.core.Either
import com.biondic.rssreader.core.model.DatabaseError

fun <SuccessModel : Any> safeDatabaseCall(
    operation: () -> SuccessModel,
): Either<DatabaseError, SuccessModel> = Either.catch {
    operation()
}.mapLeft {
    DatabaseError.DatabaseOperationError(it.message.orEmpty())
}
