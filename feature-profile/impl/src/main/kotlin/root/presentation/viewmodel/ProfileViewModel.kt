package root.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import root.domain.GetProfileUseCase
import root.presentation.compose.model.ProfileItemType
import root.presentation.compose.model.ProfileItemUiModel
import root.presentation.viewmodel.model.ProfileAction
import root.presentation.viewmodel.model.ProfileEvent
import root.presentation.viewmodel.model.ProfileState

class ProfileViewModel : BaseViewModel<ProfileState, ProfileAction, ProfileEvent>(
    initialState = ProfileState(isLoading = true)
), KoinComponent {
    private val getProfile by inject<GetProfileUseCase>()
    private val appDispatchers by inject<AppDispatchers>()

    init {
        loadContent()
    }

    override fun obtainEvent(viewEvent: ProfileEvent) {
        when (viewEvent) {
            is ProfileEvent.OnEditProfileClick -> onEditProfileClick()
            is ProfileEvent.OnListItemClick -> onListItemClick(viewEvent.uiModel)
            is ProfileEvent.OnRetryClick -> loadContent()
            is ProfileEvent.ResetAction -> onResetAction()
        }
    }

    private fun loadContent() {
        launchJob(context = appDispatchers.network, onError = {
            viewState = viewState.copy(isLoading = false, isError = true)
        }) {
            viewState = viewState.copy(isLoading = true, isError = false)
            val profile = getProfile()
            viewState = ProfileState(
                name = profile.name,
                organizationName = profile.organizationName,
                phone = profile.phone,
                email = profile.email,
                isLoading = false
            )
        }
    }

    private fun onEditProfileClick() {
        viewAction = ProfileAction.OpenEditProfile
    }

    private fun onListItemClick(uiModel: ProfileItemUiModel) {
        viewAction = when (uiModel.type) {
            ProfileItemType.DepartureAddress -> ProfileAction.OpenDepartureAddress
            ProfileItemType.Support -> ProfileAction.OpenSupport
            ProfileItemType.Transport -> ProfileAction.OpenTransport
            ProfileItemType.Notifications -> ProfileAction.OpenNotificationsSettings
            ProfileItemType.Offer -> ProfileAction.OpenOffer
            ProfileItemType.Exit -> ProfileAction.OpenExitFromAccount
        }
    }
}