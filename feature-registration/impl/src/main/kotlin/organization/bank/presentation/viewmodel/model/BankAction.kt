package organization.bank.presentation.viewmodel.model

sealed interface BankAction {
    object OpenPreviousStep : BankAction
    object OpenNextStep : BankAction
}