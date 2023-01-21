package additionalInfo.presentation.viewmodel.model

sealed interface AdditionalInfoEvent {
    object OnBackClickEvent: AdditionalInfoEvent
}