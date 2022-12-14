package auth.data

import data.AuthRepository
import data.model.request.SendVerifyCodeRequest
import data.model.request.SignInRequest
import data.model.request.SignUpRequest

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {
    override suspend fun getVerifyCode(request: SendVerifyCodeRequest) {
        api.getVerifyCode(request)
    }

    override suspend fun signIn(request: SignInRequest) {
        api.signIn(request)
    }

    override suspend fun signUp(request: SignUpRequest) {
        api.signUp(request)
    }
}