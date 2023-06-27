package root.data.mapper

import domain.model.CompanyInfoModel
import root.data.model.response.companyinfo.CompanyInfoDto

class CompanyInfoMapper {
    fun map(companyInfo: CompanyInfoDto) = CompanyInfoModel(
        name = companyInfo.companyName,
        kpp = companyInfo.details.kpp,
        ogrn = companyInfo.details.ogrn,
        legalAddress = companyInfo.details.address.value
    )
}