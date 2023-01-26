package network.exceptions

import java.io.IOException

class NotFoundException(cause: Throwable, message: String? = cause.localizedMessage) :
    IOException(message, cause)