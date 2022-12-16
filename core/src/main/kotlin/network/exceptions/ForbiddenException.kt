package network.exceptions

import java.io.IOException

class ForbiddenException(cause: Throwable, message: String? = cause.localizedMessage) :
    IOException(message, cause)