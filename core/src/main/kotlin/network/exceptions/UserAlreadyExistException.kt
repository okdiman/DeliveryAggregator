package network.exceptions

import java.io.IOException

class UserAlreadyExistException(cause: Throwable, message: String? = cause.localizedMessage) :
    IOException(message, cause)