package data

import data.model.request.SignInRequest
import data.model.request.SignUpRequest
import domain.model.VerifyCodeModel

interface AuthRepository {
    suspend fun getVerifyCode(request: VerifyCodeModel)
    suspend fun signIn(request: SignInRequest)
    suspend fun signUp(request: SignUpRequest)
}