package network.exceptions

import java.io.IOException

class UnauthorizedException(cause: Throwable, message: String? = cause.localizedMessage) :
    IOException(message, cause)