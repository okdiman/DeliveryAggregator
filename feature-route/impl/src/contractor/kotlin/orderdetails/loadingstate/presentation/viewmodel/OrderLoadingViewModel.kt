package orderdetails.loadingstate.presentation.viewmodel

import BaseViewModel
import android.net.Uri
import coroutines.AppDispatchers
import domain.LoadImageUseCase
import orderdetails.cargotype.domain.model.OrderLoadingCargoType
import orderdetails.loadingstate.domain.ConfirmLoadingStateUseCase
import orderdetails.loadingstate.domain.GetExtrasUseCase
import orderdetails.loadingstate.domain.model.LoadingStateRequestModel
import orderdetails.loadingstate.presentation.compose.model.OrderLoadingExtrasUiModel
import orderdetails.loadingstate.presentation.compose.model.OrderLoadingParamState
import orderdetails.loadingstate.presentation.mapper.ExtrasUiMapper
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingAction
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingEvent
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingState
import orderdetails.root.presentation.OrderStatesParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import permissions.AppPermissionState
import permissions.PermissionsConstants
import permissions.domain.interactor.PermissionsInteractor
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants.Helpers.COMMA
import utils.CurrentDateUtil
import utils.ext.DateFormats.FULL_DISPLAYED_DAY_MONTH_FORMATTER
import utils.ext.DateFormats.TIME_FORMATTER
import utils.resource.domain.ResourceInteractor
import view.model.PhotoParamState

class OrderLoadingViewModel(private val parameters: OrderStatesParameters) :
    BaseViewModel<OrderLoadingState, OrderLoadingAction, OrderLoadingEvent>(initialState = OrderLoadingState()),
    KoinComponent {

    private val currentDateUtil by inject<CurrentDateUtil>()
    private val resourceInteractor by inject<ResourceInteractor>()
    private val permission by inject<PermissionsInteractor>()
    private val loadImage by inject<LoadImageUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val confirmLoadingState by inject<ConfirmLoadingStateUseCase>()
    private val getExtras by inject<GetExtrasUseCase>()
    private val extrasMapper by inject<ExtrasUiMapper>()

    init {
        if (permission.isPermissionGranted(PermissionsConstants.Camera)) {
            viewState = viewState.copy(cameraPermissionState = AppPermissionState.Granted)
        }
        launchJob(appDispatchers.network) {
            val extras = getExtras()
            viewState = viewState.copy(
                extras = OrderLoadingParamState.ExtrasState(uiModel = extrasMapper.map(extras))
            )
        }
    }

    override fun obtainEvent(viewEvent: OrderLoadingEvent) {
        when (viewEvent) {
            is OrderLoadingEvent.OnExtrasChanged -> onExtrasChanged(viewEvent.extras)
            is OrderLoadingEvent.OnBoxesCountChanged -> onBoxesCountChanged(viewEvent.count)
            is OrderLoadingEvent.OnCargoTypeChanged -> onCargoTypeChanged(viewEvent.type)
            is OrderLoadingEvent.OnPalletsCountChanged -> onPalletsCountChanged(viewEvent.count)
            is OrderLoadingEvent.OnPhotoAdded -> onPhotoAdded(viewEvent.uri)
            is OrderLoadingEvent.OnPermissionStateChanged -> onPermissionStateChanged(viewEvent.state)
            OrderLoadingEvent.OnBackClick -> onBackClick()
            OrderLoadingEvent.OnDoneButtonClick -> onDoneButtonClick()
            OrderLoadingEvent.OnPhotoClick -> onPhotoClick()
            OrderLoadingEvent.ResetAction -> onResetAction()
            OrderLoadingEvent.OnOpenExtrasBSClick -> onOpenExtrasBSClick()
            OrderLoadingEvent.OnOpenCargoTypeBSClick -> onOpenCargoTypeBSClick()
            OrderLoadingEvent.OnRationaleDismiss -> onRationaleDismiss()
        }
    }

    private fun onExtrasChanged(extras: List<OrderLoadingExtrasUiModel>) {
        viewState = viewState.copy(
            extras = OrderLoadingParamState.ExtrasState(
                stateText = extras.joinToString(COMMA) { it.text },
                extrasActive = extras,
                uiModel = viewState.extras.uiModel
            ),
            isDoneButtonVisible = inDoneButtonVisible(extras = extras.map { it.id })
        )
    }

    private fun onPhotoAdded(uri: Uri) {
        val date = currentDateUtil.getDate(
            formatter = arrayOf(FULL_DISPLAYED_DAY_MONTH_FORMATTER, TIME_FORMATTER),
            separator = resourceInteractor.getString(R.string.loading_in_time_separator)
        )
        launchJob(
            context = appDispatchers.network,
            onError = {
                viewState = viewState.copy(photo = null)
            }) {
            val remoteLink = loadImage(uri)
            viewState = viewState.copy(
                photo = PhotoParamState(uri = uri, date = date, remoteLink = remoteLink, isLoading = false),
                isDoneButtonVisible = inDoneButtonVisible(remoteLink = remoteLink)
            )
        }
        viewState = viewState.copy(photo = PhotoParamState(uri = uri, date = date, isLoading = true))
    }

    private fun onBoxesCountChanged(count: String) {
        viewState = viewState.copy(
            boxesCount = OrderLoadingParamState.BoxesCountState(
                stateText = count
            ),
            isDoneButtonVisible = inDoneButtonVisible(boxesCount = count)
        )
    }

    private fun onCargoTypeChanged(cargoType: OrderLoadingCargoType) {
        viewState = viewState.copy(
            cargoType = OrderLoadingParamState.CargoTypeState(
                stateText = cargoType.text,
                cargoType = cargoType
            ),
            isDoneButtonVisible = inDoneButtonVisible(cargoType = cargoType)
        )
    }

    private fun onPalletsCountChanged(count: String) {
        viewState = viewState.copy(
            palletsCount = OrderLoadingParamState.PalletsCountState(
                stateText = count,
            ),
            isDoneButtonVisible = inDoneButtonVisible(palletsCount = count)
        )
    }

    private fun onBackClick() {
        viewAction = OrderLoadingAction.OpenPreviousScreen
    }

    private fun onDoneButtonClick() {
        launchJob(appDispatchers.network) {
            confirmLoadingState(parameters.id, getLoadingStateRequest())
            viewAction = OrderLoadingAction.OpenPreviousScreen
        }
    }

    private fun onPhotoClick() {
        viewAction = OrderLoadingAction.OpenCamera
    }

    private fun onOpenCargoTypeBSClick() {
        viewAction = OrderLoadingAction.OpenCargoTypeScreen
    }

    private fun onOpenExtrasBSClick() {
        viewAction = OrderLoadingAction.OpenExtrasScreen
    }

    private fun onPermissionStateChanged(state: AppPermissionState) {
        launchJob {
            when (state) {
                AppPermissionState.Rationale -> {
                    if (!permission.isShowRationaleDismissed(PermissionsConstants.Camera)) {
                        viewState = viewState.copy(cameraPermissionState = state)
                    }
                }
                else -> {
                    viewState = viewState.copy(cameraPermissionState = state)
                }
            }
        }
    }

    private fun onRationaleDismiss() {
        launchJob {
            viewState = viewState.copy(cameraPermissionState = AppPermissionState.Denied)
            permission.setRationaleDismissed(PermissionsConstants.Camera)
        }
    }

    private fun getLoadingStateRequest() = LoadingStateRequestModel(
        boxes = viewState.boxesCount.stateText.toInt(),
        pallets = viewState.palletsCount.stateText.toInt(),
        cargoType = viewState.cargoType.cargoType?.text.orEmpty(),
        images = listOf(viewState.photo?.remoteLink.orEmpty()),
        extras = viewState.extras.extrasActive.filterNot { it == OrderLoadingExtrasUiModel.Default }.map { it.id }
    )

    private fun inDoneButtonVisible(
        boxesCount: String = viewState.boxesCount.stateText,
        palletsCount: String = viewState.palletsCount.stateText,
        cargoType: OrderLoadingCargoType? = viewState.cargoType.cargoType,
        extras: List<Long> = viewState.extras.extrasActive.map { it.id },
        remoteLink: String? = viewState.photo?.remoteLink
    ) = boxesCount.isNotEmpty() && palletsCount.isNotEmpty() && cargoType != null && extras.isNotEmpty() &&
        remoteLink != null
}