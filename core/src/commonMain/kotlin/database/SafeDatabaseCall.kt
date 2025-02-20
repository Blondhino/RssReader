package database

import arrow.core.Either
import model.DatabaseError

fun <SuccessModel : Any> safeDatabaseCall(
    operation: () -> SuccessModel,
): Either<DatabaseError, SuccessModel> = Either.catch {
    operation()
}.mapLeft {
    DatabaseError.DatabaseOperationError(it.message.orEmpty())
}
