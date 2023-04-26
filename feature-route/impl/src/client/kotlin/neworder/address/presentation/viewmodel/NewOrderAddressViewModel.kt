package neworder.address.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import data.AddressConstants
import domain.model.AddressModel
import domain.model.request.AddressSuggestRequestModel
import domain.usecase.AddUserAddressUseCase
import domain.usecase.GetSuggestByQueryUseCase
import domain.usecase.GetUserAddressesUseCase
import domain.usecase.UpdateUserAddressUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import neworder.address.presentation.viewmodel.model.NewOrderAddressAction
import neworder.address.presentation.viewmodel.model.NewOrderAddressEvent
import neworder.address.presentation.viewmodel.model.NewOrderAddressState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.AddressSuggestUiModel
import presentation.AddressUiConstants.ADDRESSES_MAX_COUNT
import presentation.mapper.AddressUiMapper
import presentation.model.AddressUiModel
import root.presentation.mapper.AddressSuggestUiMapper

class NewOrderAddressViewModel : BaseViewModel<NewOrderAddressState, NewOrderAddressAction, NewOrderAddressEvent>(
    initialState = NewOrderAddressState()
), KoinComponent {
    private val appDispatchers by inject<AppDispatchers>()
    private val addUserAddress by inject<AddUserAddressUseCase>()
    private val updateUserAddress by inject<UpdateUserAddressUseCase>()
    private val getSuggestByQuery by inject<GetSuggestByQueryUseCase>()
    private val suggestUiMapper by inject<AddressSuggestUiMapper>()
    private val getUserAddresses by inject<GetUserAddressesUseCase>()
    private val mapper by inject<AddressUiMapper>()

    private var addresses = emptyList<AddressModel>()
    private var suggestJob: Job? = null

    init {
        loadContent()
    }

    override fun obtainEvent(viewEvent: NewOrderAddressEvent) {
        when (viewEvent) {
            is NewOrderAddressEvent.OnAddressClick -> onAddressClick(viewEvent.model)
            is NewOrderAddressEvent.OnEditClick -> onEditAddressClick(viewEvent.uiModel)
            is NewOrderAddressEvent.OnBSAddressChanged -> onBsAddressChanged(viewEvent.bsAddress)
            is NewOrderAddressEvent.OnSuggestAddressClick -> onSuggestAddressClick(viewEvent.id, viewEvent.address)
            NewOrderAddressEvent.OnRetryClick -> loadContent()
            NewOrderAddressEvent.OnAddAddressClick -> onAddAddressClick()
            NewOrderAddressEvent.OnBackClick -> onBackClick()
            NewOrderAddressEvent.ResetAction -> onResetAction()
        }
    }

    private fun loadContent() {
        launchJob(context = appDispatchers.network, onError = {
            viewState = viewState.copy(isError = true, isLoading = false)
        }) {
            addresses = getUserAddresses()
            val uiModels = mapper.mapToUi(addresses)
            viewState = viewState.copy(
                addresses = uiModels,
                isLoading = false,
                isError = false
            )
            viewAction = uiModels.find { it.isActive }?.let { NewOrderAddressAction.UpdateNewOrderScreen(it) }
        }
    }

    private fun onSuggestAddressClick(id: Long, address: AddressSuggestUiModel) {
        if (address.house.isEmpty() || !address.isFinal) {
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
                                street = address.street,
                                comment = address.comment
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

    private fun onAddressClick(model: AddressUiModel) {
        launchJob(context = appDispatchers.network) {
            addresses = addresses.map {
                if (it.id == model.id) {
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
            addresses.find { it.id == model.id }?.let {
                updateUserAddress(it)
            }
            viewAction = NewOrderAddressAction.UpdateNewOrderScreen(model)
        }
    }

    private fun onEditAddressClick(uiModel: AddressUiModel) {
        viewState = viewState.copy(
            bsAddress = viewState.bsAddress.copy(
                stateText = uiModel.address
            )
        )
        viewAction = NewOrderAddressAction.OpenAddressEdit(uiModel.id)
    }

    private fun onBackClick() {
        viewAction = NewOrderAddressAction.OpenPreviousScreen
    }

    private fun onAddAddressClick() {
        viewAction = if (viewState.addresses.size < ADDRESSES_MAX_COUNT) {
            viewState = viewState.copy(bsAddress = viewState.bsAddress.copy(stateText = ""))
            NewOrderAddressAction.OpenAddAddress
        } else {
            NewOrderAddressAction.OpenAddingError
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
                    suggests = suggestUiMapper.map(suggests).filter { it.subtitle.isNotBlank() },
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
        internal const val NEW_ID = Long.MIN_VALUE
    }
}
