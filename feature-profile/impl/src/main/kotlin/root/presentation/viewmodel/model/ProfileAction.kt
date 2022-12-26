package root.presentation.viewmodel.model

sealed class ProfileAction {
    object OpenEditProfile : ProfileAction()
    object OpenDepartureAddress : ProfileAction()
    object OpenTransport : ProfileAction()
    object OpenOffer : ProfileAction()
    object OpenNotificationsSettings : ProfileAction()
    object OpenSupport : ProfileAction()
    object OpenExitFromAccount : ProfileAction()
}