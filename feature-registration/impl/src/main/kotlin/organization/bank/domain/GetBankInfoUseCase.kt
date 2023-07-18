package organization.bank.domain

import domain.AuthRepository

class GetBankInfoUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(bik: String, code: Int, phone: String) =
        repository.getBankInfoByBik(bik, code, phone)
}