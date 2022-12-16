package root.data

import data.AuthRepository
import data.model.request.SendVerifyCodeRequest
import data.model.request.SignInRequest
import data.model.request.SignUpRequest
import domain.model.SignInModel
import domain.model.VerifyCodeModel

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {
    override suspend fun getVerifyCode(model: VerifyCodeModel) {
        api.getVerifyCode(SendVerifyCodeRequest(model.phone))
    }

    override suspend fun signIn(model: SignInModel) {
        api.signIn(SignInRequest(model.code, model.phone))
    }

    override suspend fun signUp(model: SignUpRequest) {
        api.signUp(model)
    }
}