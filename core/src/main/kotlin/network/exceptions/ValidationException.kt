package network.exceptions

import java.io.IOException

class ValidationException(cause: Throwable, message: String? = cause.localizedMessage) :
    IOException(message, cause)