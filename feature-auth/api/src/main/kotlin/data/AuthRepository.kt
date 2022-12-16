package data

import data.model.request.SignUpRequest
import domain.model.SignInModel
import domain.model.VerifyCodeModel

interface AuthRepository {
    suspend fun getVerifyCode(model: VerifyCodeModel)
    suspend fun signIn(model: SignInModel)
    suspend fun signUp(model: SignUpRequest)
}