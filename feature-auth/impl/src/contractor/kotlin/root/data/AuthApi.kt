package root.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import root.data.model.AuthSignUpRequest
import root.data.model.request.AuthSendVerifyCodeRequest
import root.data.model.request.AuthSignInRequest
import root.data.model.request.BikBankRequest
import root.data.model.request.InnCompanyRequest
import root.data.model.response.AuthSuccessDto
import root.data.model.response.bankinfo.BankInfoDto
import root.data.model.response.companyinfo.CompanyInfoDto

interface AuthApi {
    @POST("/auth/send-code")
    suspend fun getVerifyCode(
        @Body request: AuthSendVerifyCodeRequest
    )

    @POST("/auth/sign-in")
    suspend fun signIn(
        @Body request: AuthSignInRequest
    ): AuthSuccessDto

    @POST("/auth/contractor/sign-up")
    suspend fun signUp(
        @Body request: AuthSignUpRequest
    ): AuthSuccessDto

    /**
     * возвращает инфу о токене, но пока она не нужна и просто проверяем 200 или нет
     */
    @GET("/auth/me")
    suspend fun getAuthInfo()

    @POST("/auth/suggest/party")
    suspend fun getCompanyInfoByInn(
        @Body request: InnCompanyRequest
    ): ArrayList<CompanyInfoDto>

    @POST("/auth/suggest/bank")
    suspend fun getBankInfoByBik(
        @Body request: BikBankRequest
    ): ArrayList<BankInfoDto>
}