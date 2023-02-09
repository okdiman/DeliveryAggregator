package orderdetails.loadingstate.presentation.viewmodel

import BaseViewModel
import android.net.Uri
import orderdetails.cargotype.domain.model.OrderLoadingCargoType
import orderdetails.loadingstate.presentation.OrderStateParameters
import orderdetails.loadingstate.presentation.compose.model.OrderLoadingParamState
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingAction
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingEvent
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingState
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

class OrderLoadingViewModel(private val parameters: OrderStateParameters) :
    BaseViewModel<OrderLoadingState, OrderLoadingAction, OrderLoadingEvent>(
        initialState = OrderLoadingState()
    ), KoinComponent {

    private val currentDateUtil by inject<CurrentDateUtil>()
    private val resourceInteractor by inject<ResourceInteractor>()
    private val permission by inject<PermissionsInteractor>()

    init {
        if (permission.isPermissionGranted(PermissionsConstants.Camera)) {
            viewState = viewState.copy(cameraPermissionState = AppPermissionState.Granted)
        }
    }

    override fun obtainEvent(viewEvent: OrderLoadingEvent) {
        when (viewEvent) {
            is OrderLoadingEvent.OnAdditionalOptionsChanged -> onAdditionalOptionsChanged(viewEvent.options)
            is OrderLoadingEvent.OnBoxesCountChanged -> onBoxesCountChanged(viewEvent.count)
            is OrderLoadingEvent.OnCargoTypeChanged -> onCargoTypeChanged(viewEvent.type)
            is OrderLoadingEvent.OnPalletsCountChanged -> onPalletsCountChanged(viewEvent.count)
            is OrderLoadingEvent.OnPhotoAdded -> onPhotoAdded(viewEvent.uri)
            is OrderLoadingEvent.OnPermissionStateChanged -> onNotificationPermissionStateChanged(viewEvent.state)
            OrderLoadingEvent.OnBackClick -> onBackClick()
            OrderLoadingEvent.OnDoneButtonClick -> onDoneButtonClick()
            OrderLoadingEvent.OnPhotoClick -> onPhotoClick()
            OrderLoadingEvent.ResetAction -> onResetAction()
            OrderLoadingEvent.OnOpenAdditionalOptBSClick -> onOpenAdditionalOptBSClick()
            OrderLoadingEvent.OnOpenCargoTypeBSClick -> onOpenCargoTypeBSClick()
            OrderLoadingEvent.OnRationaleDismiss -> onRationaleDismiss()
        }
    }

    private fun onAdditionalOptionsChanged(options: List<String>) {
        viewState = viewState.copy(
            additionalOptions = OrderLoadingParamState.AdditionalOptionsState(
                stateText = options.joinToString(COMMA),
                optionsList = options
            )
        )
    }

    private fun onPhotoAdded(uri: Uri) {
        val date = currentDateUtil.getDate(
            formatter = arrayOf(FULL_DISPLAYED_DAY_MONTH_FORMATTER, TIME_FORMATTER),
            separator = resourceInteractor.getString(R.string.loading_in_time_separator)
        )
        viewState = viewState.copy(photo = PhotoParamState(uri = uri, date = date))
    }

    private fun onBoxesCountChanged(count: String) {
        viewState = viewState.copy(
            boxesCount = OrderLoadingParamState.BoxesCountState(
                stateText = count
            )
        )
    }

    private fun onCargoTypeChanged(cargoType: OrderLoadingCargoType) {
        viewState = viewState.copy(
            cargoType = OrderLoadingParamState.CargoTypeState(
                stateText = cargoType.text,
                cargoType = cargoType
            )
        )
    }

    private fun onPalletsCountChanged(count: String) {
        viewState = viewState.copy(
            palletsCount = OrderLoadingParamState.PalletsCountState(
                stateText = count
            )
        )
    }

    private fun onBackClick() {
        viewAction = OrderLoadingAction.OpenPreviousScreen
    }

    private fun onDoneButtonClick() {
        //TODO запрос на смену статуса
        viewAction = OrderLoadingAction.OpenPreviousScreen
    }

    private fun onPhotoClick() {
        viewAction = OrderLoadingAction.OpenCamera
    }

    private fun onOpenCargoTypeBSClick() {
        viewAction = OrderLoadingAction.OpenCargoTypeScreen
    }

    private fun onOpenAdditionalOptBSClick() {
        viewAction = OrderLoadingAction.OpenAdditionalOptionsScreen
    }

    private fun onNotificationPermissionStateChanged(state: AppPermissionState) {
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
}