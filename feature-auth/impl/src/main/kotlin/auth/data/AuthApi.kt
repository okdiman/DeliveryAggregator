package auth.data

import data.model.request.SendVerifyCodeRequest
import data.model.request.SignInRequest
import data.model.request.SignUpRequest
import data.model.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/send-codeasd")
    suspend fun getVerifyCode(
        @Body request: SendVerifyCodeRequest
    )

    @POST("/auth/sign-in")
    suspend fun signIn(
        @Body request: SignInRequest
    ): SignInResponse

    @POST("/auth/contractor/sign-up")
    suspend fun signUp(
        @Body request: SignUpRequest
    )
}