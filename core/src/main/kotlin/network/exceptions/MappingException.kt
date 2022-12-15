package network.exceptions

import java.io.IOException

class MappingException(cause: Throwable, message: String? = cause.localizedMessage) :
    IOException(message, cause)