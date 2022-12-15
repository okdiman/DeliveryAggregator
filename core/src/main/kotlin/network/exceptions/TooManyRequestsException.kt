package network.exceptions

import java.io.IOException

class TooManyRequestsException(cause: Throwable, message: String? = cause.localizedMessage) :
    IOException(message, cause)