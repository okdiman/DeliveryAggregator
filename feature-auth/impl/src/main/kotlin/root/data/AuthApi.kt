package root.data

import root.data.model.request.AuthSendVerifyCodeRequest
import root.data.model.request.AuthSignInRequest
import root.data.model.request.AuthSignUpRequest
import root.data.model.response.AuthSuccessResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/send-code")
    suspend fun getVerifyCode(
        @Body request: AuthSendVerifyCodeRequest
    )

    @POST("/auth/sign-in")
    suspend fun signIn(
        @Body request: AuthSignInRequest
    ): AuthSuccessResponse

    @POST("/auth/contractor/sign-up")
    suspend fun signUp(
        @Body request: AuthSignUpRequest
    ): AuthSuccessResponse

    /**
     * возвращает инфу о токене, но пока она не нужна
     */
    @GET("/auth/me")
    suspend fun getAuthInfo()
}