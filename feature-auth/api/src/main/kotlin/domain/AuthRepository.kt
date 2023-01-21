package domain

import domain.model.AuthSignInModel
import domain.model.AuthSignUpModel
import domain.model.AuthVerifyCodeModel

interface AuthRepository {
    suspend fun getAuthInfo()
    suspend fun getVerifyCode(model: AuthVerifyCodeModel)
    suspend fun signIn(model: AuthSignInModel)
    suspend fun signUp(model: AuthSignUpModel)
}