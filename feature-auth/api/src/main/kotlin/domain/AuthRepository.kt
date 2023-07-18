package domain

import domain.model.AuthSignInModel
import domain.model.AuthSignUpModel
import domain.model.AuthVerifyCodeModel
import domain.model.BankInfoModel
import domain.model.CompanyInfoModel

interface AuthRepository {
    suspend fun clearToken()
    suspend fun isAuthorized(): Boolean
    suspend fun getAuthInfo()
    suspend fun getVerifyCode(model: AuthVerifyCodeModel)
    suspend fun signIn(model: AuthSignInModel)
    suspend fun signUp(model: AuthSignUpModel)
    suspend fun getCompanyInfoByInn(inn: String, code: Int, phone: String): CompanyInfoModel?
    suspend fun getBankInfoByBik(bik: String, code: Int, phone: String): BankInfoModel?
}