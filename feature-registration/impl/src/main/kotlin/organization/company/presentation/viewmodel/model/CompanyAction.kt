package organization.company.presentation.viewmodel.model

sealed interface CompanyAction {
    object OpenNextStep : CompanyAction
    object OpenSelectLegalAddress : CompanyAction
    object OpenSelectActualAddress : CompanyAction
}