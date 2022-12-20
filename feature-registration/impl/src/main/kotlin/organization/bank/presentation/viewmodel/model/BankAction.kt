package organization.bank.presentation.viewmodel.model

sealed class BankAction {
    object OpenPreviousStep : BankAction()
    object OpenNextStep : BankAction()
}
