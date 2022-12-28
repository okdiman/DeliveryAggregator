package root.data

import data.AuthRepository
import data.datasource.AuthLocalDataSource
import data.model.request.SendVerifyCodeRequest
import data.model.request.SignInRequest
import domain.model.SignInModel
import domain.model.SignUpModel
import domain.model.VerifyCodeModel
import root.data.mapper.SignUpMapper

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val localDataSource: AuthLocalDataSource,
    private val mapper: SignUpMapper
) : AuthRepository {
    override suspend fun getVerifyCode(model: VerifyCodeModel) {
        api.getVerifyCode(SendVerifyCodeRequest(model.phone))
    }

    override suspend fun signIn(model: SignInModel) {
        val response = api.signIn(SignInRequest(model.code, model.phone))
        localDataSource.saveToken(response.token)
    }

    override suspend fun signUp(model: SignUpModel) {
        val response = api.signUp(mapper.map(model))
        localDataSource.saveToken(response.token)
    }

    override suspend fun getAuthInfo() {
        api.getAuthInfo()
    }
}