package network.exceptions

import java.io.IOException

class ServerException(cause: Throwable, message: String? = cause.localizedMessage) :
    IOException(message, cause)