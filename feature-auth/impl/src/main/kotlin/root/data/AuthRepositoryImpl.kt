package root.data

import data.AuthRepository
import data.datastore.AuthLocalDataStore
import data.model.request.SendVerifyCodeRequest
import data.model.request.SignInRequest
import domain.model.SignInModel
import domain.model.SignUpModel
import domain.model.VerifyCodeModel
import root.data.mapper.SignUpMapper

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val localDataStore: AuthLocalDataStore,
    private val mapper: SignUpMapper
) : AuthRepository {
    override suspend fun getVerifyCode(model: VerifyCodeModel) {
        api.getVerifyCode(SendVerifyCodeRequest(model.phone))
    }

    override suspend fun signIn(model: SignInModel) {
        val response = api.signIn(SignInRequest(model.code, model.phone))
        localDataStore.saveToken(response.token)
    }

    override suspend fun signUp(model: SignUpModel) {
        val response = api.signUp(mapper.map(model))
        localDataStore.saveToken(response.token)
    }
}