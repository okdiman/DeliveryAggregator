package departure.root.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import data.AddressConstants
import departure.root.domain.AddUserAddressUseCaseImpl
import departure.root.presentation.compose.model.DepartureAddressUiModel
import departure.root.presentation.mapper.DepartureAddressUiMapper
import departure.root.presentation.viewmodel.model.DepartureAction
import departure.root.presentation.viewmodel.model.DepartureEvent
import departure.root.presentation.viewmodel.model.DepartureState
import domain.model.AddressModel
import domain.model.request.AddressSuggestRequestModel
import domain.usecase.GetSuggestByQueryUseCase
import domain.usecase.GetUserAddressesUseCase
import domain.usecase.UpdateUserAddressUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.mapper.AddressSuggestUiMapper
import presentation.model.AddressUiModel

class DepartureViewModel : BaseViewModel<DepartureState, DepartureAction, DepartureEvent>(
    initialState = DepartureState()
), KoinComponent {

    private val addUserAddress by inject<AddUserAddressUseCaseImpl>()
    private val updateUserAddress by inject<UpdateUserAddressUseCase>()
    private val getSuggestByQuery by inject<GetSuggestByQueryUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val addressUiMapper by inject<AddressSuggestUiMapper>()
    private val getUserAddresses by inject<GetUserAddressesUseCase>()
    private val mapper by inject<DepartureAddressUiMapper>()

    private var addresses = emptyList<AddressModel>()
    private var suggestJob: Job? = null

    init {
        loadContent()
    }

    override fun obtainEvent(viewEvent: DepartureEvent) {
        when (viewEvent) {
            is DepartureEvent.OnAddressClick -> onAddressClick(viewEvent.id)
            is DepartureEvent.OnEditClick -> onEditAddressClick(viewEvent.uiModel)
            is DepartureEvent.OnBSAddressChanged -> onBsAddressChanged(viewEvent.bsAddress)
            is DepartureEvent.OnSuggestAddressClick ->
                onSuggestAddressClick(viewEvent.id, viewEvent.address)
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
                addresses = mapper.mapToUi(addresses),
                isLoading = false,
                isError = false
            )
        }
    }

    private fun onSuggestAddressClick(id: String, address: AddressUiModel) {
        if (address.house.isEmpty()) {
            viewState = viewState.copy(
                bsAddress = viewState.bsAddress.copy(
                    stateText = address.subtitle
                )
            )
        } else {
            launchJob(appDispatchers.network) {
                viewState = viewState.copy(isLoading = true, isError = false)
                if (id == NEW_ID) {
                    addUserAddress(mapper.mapToDomain(address, id))
                } else {
                    addresses.find { it.id == id }?.let {
                        updateUserAddress(
                            it.copy(
                                city = address.city,
                                house = address.house,
                                street = address.street
                            )
                        )
                    }
                }
                loadContent()
            }
        }
    }

    private fun onBsAddressChanged(address: String) {
        loadSuggests(address)
        viewState = viewState.copy(
            bsAddress = viewState.bsAddress.copy(
                stateText = address
            )
        )
    }

    private fun onAddressClick(id: String) {
        launchJob(context = appDispatchers.network) {
            addresses = addresses.map {
                if (it.id == id) {
                    it.copy(isActive = true)
                } else {
                    it.copy(isActive = false)
                }
            }
            viewState = viewState.copy(
                addresses = mapper.mapToUi(addresses),
                isLoading = false,
                isError = false
            )
            addresses.find { it.id == id }?.let {
                updateUserAddress(it)
            }
        }
    }

    private fun onEditAddressClick(uiModel: DepartureAddressUiModel) {
        viewState = viewState.copy(
            bsAddress = viewState.bsAddress.copy(
                stateText = uiModel.address
            )
        )
        viewAction = DepartureAction.OpenAddressEdit(uiModel.id)
    }

    private fun onBackClick() {
        viewAction = DepartureAction.OpenPreviousScreen
    }

    private fun onAddAddressClick() {
        viewAction = if (viewState.addresses.size < MAX_ADDRESS) {
            viewState = viewState.copy(bsAddress = viewState.bsAddress.copy(stateText = ""))
            DepartureAction.OpenAddAddress
        } else {
            DepartureAction.OpenAddingError
        }
    }

    private fun loadSuggests(query: String) {
        suggestJob?.cancel()
        if (query.length >= AddressConstants.MIN_CHARS_FOR_SUGGEST) {
            suggestJob = launchJob(context = appDispatchers.network, onError = {
                viewState = viewState.copy(
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = false)
                )
            }) {
                delay(AddressConstants.DEBOUNCE)
                viewState = viewState.copy(
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = true)
                )
                val suggests = getSuggestByQuery(AddressSuggestRequestModel(query = query))
                viewState = viewState.copy(
                    suggests = addressUiMapper.map(suggests),
                    bsAddress = viewState.bsAddress.copy(isSuggestLoading = false)
                )
            }
        } else {
            viewState = viewState.copy(
                suggests = emptyList(),
                bsAddress = viewState.bsAddress.copy(isSuggestLoading = false)
            )
        }
    }

    companion object {
        private const val MAX_ADDRESS = 6
        internal const val NEW_ID = "new_id"
    }
}