package root.presentation.compose.view

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import permissions.AppPermissionState
import permissions.PermissionsConstants.Notification
import root.presentation.viewmodel.model.RouteEvent
import root.presentation.viewmodel.model.RouteState
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.UiConstants
import view.ShouldPermissionRationaleBSScreen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun RouteCheckNotificationPermission(
    state: RouteState,
    eventHandler: (RouteEvent) -> Unit
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val notificationsPermissionState = rememberPermissionState(permission = Notification)
        val rootController = LocalRootController.current
        LaunchedEffect(key1 = Unit) {
            when {
                notificationsPermissionState.status.isGranted -> {
                    eventHandler(RouteEvent.OnPermissionStateChanged(AppPermissionState.Granted))
                }
                notificationsPermissionState.status.shouldShowRationale -> {
                    eventHandler(RouteEvent.OnPermissionStateChanged(AppPermissionState.Rationale))
                }
                else -> {
                    notificationsPermissionState.launchPermissionRequest()
                    eventHandler(RouteEvent.OnPermissionStateChanged(AppPermissionState.Denied))
                }
            }
        }
        LaunchedEffect(key1 = state.notificationsPermission) {
            if (state.notificationsPermission == AppPermissionState.Rationale) {
                rootController.findModalController().present(
                    modalSheetConfiguration = ModalSheetConfiguration(
                        cornerRadius = UiConstants.BottomSheet.SCREEN_CORNER_RADIUS
                    )
                ) {
                    ShouldPermissionRationaleBSScreen(
                        permission = Notification,
                        text = stringResource(id = R.string.route_notifications_permission),
                        onAcceptClick = {
                            notificationsPermissionState.launchPermissionRequest()
                            eventHandler(RouteEvent.OnRationaleDismiss)
                            rootController.findModalController().popBackStack(null)
                        }, onDeclineClick = {
                            eventHandler(RouteEvent.OnRationaleDismiss)
                            rootController.findModalController().popBackStack(null)
                        })
                }
            }
        }
    }
}