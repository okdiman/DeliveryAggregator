package root.data

import data.AuthLocalDataSource
import domain.AuthRepository
import domain.model.AuthSignInModel
import domain.model.AuthSignUpModel
import domain.model.AuthVerifyCodeModel
import network.exceptions.UserAlreadyExistException
import root.data.mapper.AuthSignUpMapper
import root.data.mapper.CompanyInfoMapper
import root.data.model.request.AuthSendVerifyCodeRequest
import root.data.model.request.AuthSignInRequest
import root.data.model.request.InnCompanyRequest
import trinity_monsters.delivery_aggregator.core.BuildConfig

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val localDataSource: AuthLocalDataSource,
    private val authMapper: AuthSignUpMapper,
    private val companyInfoMapper: CompanyInfoMapper
) : AuthRepository {
    override suspend fun clearToken() = localDataSource.clearToken()

    override suspend fun isAuthorized(): Boolean {
        return localDataSource.getAccessToken() != null
    }

    override suspend fun getVerifyCode(model: AuthVerifyCodeModel) {
        api.getVerifyCode(AuthSendVerifyCodeRequest(model.phone))
    }

    override suspend fun signIn(model: AuthSignInModel) {
        val response = api.signIn(AuthSignInRequest(model.code, model.phone))
        if (response.tokenInfo?.role != BuildConfig.FLAVOR) {
            throw UserAlreadyExistException(Throwable("Incorrect role"))
        }
        localDataSource.saveAccessToken(response.token)
    }

    override suspend fun signUp(model: AuthSignUpModel) {
        val response = api.signUp(authMapper.map(model))
        localDataSource.saveAccessToken(response.token)
    }

    override suspend fun getCompanyInfoByInn(inn: String, code: Int, phone: String) =
        api.getCompanyInfoByInn(InnCompanyRequest(code, phone, inn)).firstOrNull()?.let { companyInfoMapper.map(it) }

    override suspend fun getAuthInfo() {
        api.getAuthInfo()
    }
}