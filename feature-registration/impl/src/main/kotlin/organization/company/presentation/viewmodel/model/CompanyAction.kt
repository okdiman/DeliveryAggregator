package organization.company.presentation.viewmodel.model

sealed class CompanyAction {
    object OpenNextStep : CompanyAction()
    object OpenSelectLegalAddress : CompanyAction()
    object OpenSelectActualAddress : CompanyAction()
}