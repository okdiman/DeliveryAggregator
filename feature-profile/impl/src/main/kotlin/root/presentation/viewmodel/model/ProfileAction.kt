package root.presentation.viewmodel.model

sealed interface ProfileAction {
    object OpenEditProfile : ProfileAction
    object OpenDepartureAddress : ProfileAction
    object OpenTransport : ProfileAction
    object OpenOffer : ProfileAction
    object OpenDevMenu : ProfileAction
    object OpenNotificationsSettings : ProfileAction
    object OpenSupport : ProfileAction
    object OpenExitFromAccount : ProfileAction
}