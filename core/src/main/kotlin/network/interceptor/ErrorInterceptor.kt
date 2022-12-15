package network.interceptor

import java.io.IOException
import java.net.HttpURLConnection
import network.exceptions.GeneralException
import network.exceptions.MappingException
import network.exceptions.ServerException
import network.exceptions.TooManyRequestsException
import network.exceptions.ValidationException
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException

class ErrorInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = runCatching {
            chain.proceed(chain.request())
        }.getOrElse { e ->
            throw MappingException(e, e.message)
        }

        return when (response.code) {
            HTTP_STATUS_CLIENT_ERROR -> throw ValidationException(response.toHttpException())
            HTTP_STATUS_SERVER_ERROR -> throw ServerException(response.toHttpException())
            HTTP_TOO_MANY_REQUEST_ERROR -> throw TooManyRequestsException(response.toHttpException())
            in HTTP_STATUS_CLIENT_ERROR..HTTP_STATUS_SERVER_ERROR_LAST -> {
                throw GeneralException(response.toHttpException())
            }
            else -> response
        }
    }

    private fun Response.toHttpException(): HttpException {
        return HttpException(toRetrofitError())
    }

    private fun Response.toRetrofitError(): retrofit2.Response<String> {
        (body ?: "".toResponseBody()).use { body ->
            return retrofit2.Response.error(body, this)
        }
    }

    companion object {
        private const val HTTP_STATUS_CLIENT_ERROR = HttpURLConnection.HTTP_BAD_REQUEST
        private const val HTTP_TOO_MANY_REQUEST_ERROR = 429
        private const val HTTP_STATUS_SERVER_ERROR = HttpURLConnection.HTTP_INTERNAL_ERROR
        private const val HTTP_STATUS_SERVER_ERROR_LAST = 599
    }
}