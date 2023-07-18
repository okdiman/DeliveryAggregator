package root.data.mapper

import domain.model.BankInfoModel
import domain.model.CompanyInfoModel
import root.data.model.response.bankinfo.BankInfoDto
import root.data.model.response.companyinfo.CompanyInfoDto

class DtoInfoMapper {
    fun mapCompany(companyInfo: CompanyInfoDto) = CompanyInfoModel(
        name = companyInfo.companyName,
        kpp = companyInfo.details.kpp,
        ogrn = companyInfo.details.ogrn,
        legalAddress = companyInfo.details.address.value
    )

    fun mapBank(bankIfo: BankInfoDto) = BankInfoModel(
        name = bankIfo.bankName,
        correspondentAccount = bankIfo.details.correspondentAccount,
    )
}