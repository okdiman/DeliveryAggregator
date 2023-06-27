package organization.company.domain

import domain.AuthRepository

class GetCompanyInfoUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(inn: String, code: Int, phone: String) =
        repository.getCompanyInfoByInn(inn, code, phone)
}