package root.presentation.viewmodel

import BaseViewModel
import clipboard.domain.ClipboardUseCase
import coroutines.AppDispatchers
import domain.ProfileModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import root.domain.GetProfileUseCase
import root.presentation.compose.model.ProfileItemType
import root.presentation.compose.model.ProfileItemUiModel
import root.presentation.mapper.ProfileUiMapper
import root.presentation.viewmodel.model.ProfileAction
import root.presentation.viewmodel.model.ProfileEvent
import root.presentation.viewmodel.model.ProfileState

class ProfileViewModel : BaseViewModel<ProfileState, ProfileAction, ProfileEvent>(
    initialState = ProfileState(isLoading = true)
), KoinComponent {
    private val getProfile by inject<GetProfileUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val mapper by inject<ProfileUiMapper>()
    private val clipboard by inject<ClipboardUseCase>()

    lateinit var model: ProfileModel

    init {
        loadContent()
    }

    override fun obtainEvent(viewEvent: ProfileEvent) {
        when (viewEvent) {
            is ProfileEvent.OnEditProfileClick -> onEditProfileClick()
            is ProfileEvent.OnListItemClick -> onListItemClick(viewEvent.uiModel)
            is ProfileEvent.OnRetryClick -> loadContent()
            is ProfileEvent.ResetAction -> onResetAction()
            is ProfileEvent.OnPhoneLongClick -> onLongClick(viewState.phone)
            is ProfileEvent.OnEmailLongClick -> onLongClick(viewState.email)
        }
    }

    fun getProfileModel() = model

    private fun onLongClick(text: String) {
        clipboard.setText(PROFILE_LABEL, text)
    }

    private fun loadContent() {
        launchJob(context = appDispatchers.network, onError = {
            viewState = viewState.copy(isLoading = false, isError = true)
        }) {
            viewState = viewState.copy(isLoading = true, isError = false)
            getProfile().also { profile ->
                model = profile
                val uiModel = mapper.map(profile)
                viewState = ProfileState(
                    name = uiModel.name,
                    organizationName = uiModel.organizationName,
                    phone = uiModel.phone,
                    email = uiModel.email,
                    isLoading = false
                )
            }
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

    companion object {
        private const val PROFILE_LABEL = "profile_label"
    }
}