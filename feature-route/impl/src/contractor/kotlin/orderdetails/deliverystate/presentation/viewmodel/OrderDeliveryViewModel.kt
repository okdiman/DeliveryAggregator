package orderdetails.deliverystate.presentation.viewmodel

import BaseViewModel
import android.net.Uri
import coroutines.AppDispatchers
import domain.LoadImageUseCase
import orderdetails.deliverystate.domain.ConfirmDeliveryStateUseCase
import orderdetails.deliverystate.domain.model.DeliveryStateRequestModel
import orderdetails.deliverystate.presentation.compose.model.OrderDeliveryParamState
import orderdetails.deliverystate.presentation.viewmodel.model.OrderDeliveryAction
import orderdetails.deliverystate.presentation.viewmodel.model.OrderDeliveryEvent
import orderdetails.deliverystate.presentation.viewmodel.model.OrderDeliveryState
import orderdetails.root.presentation.OrderStatesParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import permissions.AppPermissionState
import permissions.PermissionsConstants
import permissions.domain.interactor.PermissionsInteractor
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CurrentDateUtil
import utils.ext.DateFormats
import utils.resource.domain.ResourceInteractor
import view.model.PhotoParamState

class OrderDeliveryViewModel(private val parameters: OrderStatesParameters) :
    BaseViewModel<OrderDeliveryState, OrderDeliveryAction, OrderDeliveryEvent>(initialState = OrderDeliveryState()),
    KoinComponent {

    private val currentDateUtil by inject<CurrentDateUtil>()
    private val resourceInteractor by inject<ResourceInteractor>()
    private val permission by inject<PermissionsInteractor>()
    private val loadImage by inject<LoadImageUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val confirmDeliveryState by inject<ConfirmDeliveryStateUseCase>()

    init {
        if (permission.isPermissionGranted(PermissionsConstants.Camera)) {
            viewState = viewState.copy(cameraPermissionState = AppPermissionState.Granted)
        }
    }

    override fun obtainEvent(viewEvent: OrderDeliveryEvent) {
        when (viewEvent) {
            is OrderDeliveryEvent.OnPermissionStateChanged -> onPermissionStateChanged(viewEvent.state)
            is OrderDeliveryEvent.OnPhotoAdded -> onPhotoAdded(viewEvent.uri)
            is OrderDeliveryEvent.OnProblemStateChanged -> onProblemStateChanged(viewEvent.isProblem)
            is OrderDeliveryEvent.OnCommentChanged -> onCommentChanged(viewEvent.comment)
            OrderDeliveryEvent.OnDoneButtonClick -> onDoneButtonClick()
            OrderDeliveryEvent.OnBackClick -> onBackClick()
            OrderDeliveryEvent.OnRationaleDismiss -> onRationaleDismiss()
            OrderDeliveryEvent.ResetAction -> onResetAction()
            OrderDeliveryEvent.OnPhotoClick -> onPhotoClick()
        }
    }

    private fun onProblemStateChanged(isProblem: Boolean) {
        viewState = viewState.copy(isProblem = isProblem)
    }

    private fun onCommentChanged(comment: String) {
        viewState = viewState.copy(comment = OrderDeliveryParamState.CommentState(stateText = comment))
    }

    private fun onPhotoAdded(uri: Uri) {
        val date = currentDateUtil.getDate(
            formatter = arrayOf(DateFormats.FULL_DISPLAYED_DAY_MONTH_FORMATTER, DateFormats.TIME_FORMATTER),
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
                isDoneButtonVisible = true
            )
        }
        viewState = viewState.copy(photo = PhotoParamState(uri = uri, date = date, isLoading = true))
    }

    private fun onDoneButtonClick() {
        launchJob(appDispatchers.network) {
            confirmDeliveryState(parameters.id, getDeliveryStateRequest())
            viewAction = OrderDeliveryAction.OpenPreviousScreen
        }
    }

    private fun onPhotoClick() {
        viewAction = OrderDeliveryAction.OpenCamera
    }

    private fun onBackClick() {
        viewAction = OrderDeliveryAction.OpenPreviousScreen
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

    private fun getDeliveryStateRequest() = DeliveryStateRequestModel(
        images = arrayListOf(viewState.photo?.remoteLink.orEmpty()),
        comment = viewState.comment.stateText,
        isProblem = viewState.isProblem
    )
}