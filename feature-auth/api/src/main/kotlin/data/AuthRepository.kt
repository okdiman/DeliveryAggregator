package data

import data.model.request.SendVerifyCodeRequest
import data.model.request.SignInRequest
import data.model.request.SignUpRequest

interface AuthRepository {
    suspend fun getVerifyCode(request: SendVerifyCodeRequest)
    suspend fun signIn(request: SignInRequest)
    suspend fun signUp(request: SignUpRequest)
}