package domain

import domain.model.SignInModel
import domain.model.SignUpModel
import domain.model.VerifyCodeModel

interface AuthRepository {
    suspend fun getAuthInfo()
    suspend fun getVerifyCode(model: VerifyCodeModel)
    suspend fun signIn(model: SignInModel)
    suspend fun signUp(model: SignUpModel)
}