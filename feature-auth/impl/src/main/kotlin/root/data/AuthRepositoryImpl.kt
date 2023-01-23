package root.data

import domain.AuthRepository
import data.AuthLocalDataSource
import root.data.model.request.AuthSendVerifyCodeRequest
import root.data.model.request.AuthSignInRequest
import domain.model.AuthSignInModel
import domain.model.AuthSignUpModel
import domain.model.AuthVerifyCodeModel
import root.data.mapper.AuthSignUpMapper

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val localDataSource: AuthLocalDataSource,
    private val mapper: AuthSignUpMapper
) : AuthRepository {
    override suspend fun getVerifyCode(model: AuthVerifyCodeModel) {
        api.getVerifyCode(AuthSendVerifyCodeRequest(model.phone))
    }

    override suspend fun signIn(model: AuthSignInModel) {
        val response = api.signIn(AuthSignInRequest(model.code, model.phone))
        localDataSource.saveAccessToken(response.token)
    }

    override suspend fun signUp(model: AuthSignUpModel) {
        val response = api.signUp(mapper.map(model))
        localDataSource.saveAccessToken(response.token)
    }

    override suspend fun getAuthInfo() {
        api.getAuthInfo()
    }
}