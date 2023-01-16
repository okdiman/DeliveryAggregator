package root.data

import root.data.model.request.SendVerifyCodeRequest
import root.data.model.request.SignInRequest
import root.data.model.request.SignUpRequest
import root.data.model.response.AuthSuccessResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/send-code")
    suspend fun getVerifyCode(
        @Body request: SendVerifyCodeRequest
    )

    @POST("/auth/sign-in")
    suspend fun signIn(
        @Body request: SignInRequest
    ): AuthSuccessResponse

    @POST("/auth/contractor/sign-up")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): AuthSuccessResponse

    /**
     * возвращает инфу о токене, но пока она не нужна
     */
    @GET("/auth/me")
    suspend fun getAuthInfo()
}