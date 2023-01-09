package departure.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import departure.presentation.DepartureAddressUiMapper
import departure.presentation.viewmodel.model.DepartureAction
import departure.presentation.viewmodel.model.DepartureEvent
import departure.presentation.viewmodel.model.DepartureState
import domain.model.AddressModel
import domain.usecase.GetUserAddressesUseCase
import domain.usecase.UpdateUserAddressUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DepartureViewModel : BaseViewModel<DepartureState, DepartureAction, DepartureEvent>(
    initialState = DepartureState()
), KoinComponent {

    private val updateUserAddress by inject<UpdateUserAddressUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val getUserAddresses by inject<GetUserAddressesUseCase>()
    private val mapper by inject<DepartureAddressUiMapper>()
    private var addresses = emptyList<AddressModel>()

    init {
        loadContent()
    }

    override fun obtainEvent(viewEvent: DepartureEvent) {
        when (viewEvent) {
            is DepartureEvent.OnAddressClick -> onAddressClick(viewEvent.id)
            is DepartureEvent.OnEditClick -> onEditAddressClick(viewEvent.id)
            DepartureEvent.OnRetryClick -> loadContent()
            DepartureEvent.OnAddAddressClick -> onAddAddressClick()
            DepartureEvent.OnBackClick -> onBackClick()
            DepartureEvent.ResetAction -> onResetAction()
        }
    }

    private fun loadContent() {
        launchJob(context = appDispatchers.network, onError = {
            viewState = viewState.copy(isError = true, isLoading = false)
        }) {
            addresses = getUserAddresses()
            viewState = viewState.copy(
                addresses = mapper.map(addresses),
                isLoading = false,
                isError = false
            )
        }
    }

    private fun onAddressClick(id: String) {
        launchJob(context = appDispatchers.network, onError = {
            viewState = viewState.copy(isLoading = false, isError = true)
        }) {
            viewState = viewState.copy(isLoading = true, isError = false)
            addresses.find { it.id == id }?.let {
                updateUserAddress(it.copy(isSelected = true))
            }
            viewState = viewState.copy(isLoading = false, addresses = viewState.addresses.map {
                it.copy(isSelected = it.id == id)
            })
        }
    }

    private fun onEditAddressClick(id: String) {
        viewAction = DepartureAction.OpenAddressEdit
    }

    private fun onBackClick() {
        viewAction = DepartureAction.OpenPreviousScreen
    }

    private fun onAddAddressClick() {
        viewAction = if (viewState.addresses.size < MAX_ADDRESS) {
            DepartureAction.OpenAddAddress
        } else {
            DepartureAction.OpenAddingError
        }
    }

    companion object {
        private const val MAX_ADDRESS = 6
    }
}