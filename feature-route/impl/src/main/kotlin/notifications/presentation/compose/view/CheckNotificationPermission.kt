package notifications.presentation.compose.view

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
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.UiConstants
import view.ShouldPermissionRationaleBSScreen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun CheckNotificationPermission(
    state: AppPermissionState?,
    onPermissionStateChanged: (AppPermissionState) -> Unit,
    onRationaleDismiss: () -> Unit,
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val notificationsPermissionState = rememberPermissionState(permission = Notification)
        val rootController = LocalRootController.current
        LaunchedEffect(key1 = Unit) {
            when {
                notificationsPermissionState.status.isGranted -> {
                    onPermissionStateChanged(AppPermissionState.Granted)
                }
                notificationsPermissionState.status.shouldShowRationale -> {
                    onPermissionStateChanged(AppPermissionState.Rationale)
                }
                else -> {
                    notificationsPermissionState.launchPermissionRequest()
                    onPermissionStateChanged(AppPermissionState.Denied)
                }
            }
        }
        LaunchedEffect(key1 = state) {
            if (state == AppPermissionState.Rationale) {
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
                            onRationaleDismiss()
                            rootController.findModalController().popBackStack(null)
                        }, onDeclineClick = {
                            onRationaleDismiss()
                            rootController.findModalController().popBackStack(null)
                        })
                }
            }
        }
    }
}
