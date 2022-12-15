package auth.data

import data.AuthRepository
import data.model.request.SendVerifyCodeRequest
import data.model.request.SignInRequest
import data.model.request.SignUpRequest
import domain.model.VerifyCodeModel

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {
    override suspend fun getVerifyCode(request: VerifyCodeModel) {
        api.getVerifyCode(SendVerifyCodeRequest(request.phone))
    }

    override suspend fun signIn(request: SignInRequest) {
        api.signIn(request)
    }

    override suspend fun signUp(request: SignUpRequest) {
        api.signUp(request)
    }
}