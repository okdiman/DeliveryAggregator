package network.exceptions

import java.io.IOException

class GeneralException(cause: Throwable, message: String? = cause.localizedMessage) :
    IOException(message, cause)