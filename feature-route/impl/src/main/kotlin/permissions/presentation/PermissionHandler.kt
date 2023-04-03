package permissions.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import openApplicationSettings
import permissions.AppPermissionState
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import utils.presentStandardBS
import view.ShouldPermissionRationaleBSScreen
import view.model.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun PermissionHandler(
    permission: String,
    title: String,
    state: PermissionState,
    onPermissionStateChanged: (AppPermissionState) -> Unit,
    onRationaleDismiss: () -> Unit = {}
) {
    val notificationsPermissionState = rememberPermissionState(permission)
    val rootController = LocalRootController.current
    val context = LocalContext.current
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
    LaunchedEffect(key1 = state.permissionState) {
        when {
            notificationsPermissionState.status.shouldShowRationale -> {
                showPermissionRationale(rootController, permission, title, onRationaleDismiss)
            }
            !notificationsPermissionState.status.isGranted && state.permissionState == AppPermissionState.Denied -> {
                openApplicationSettings(context)
            }
        }
    }
}

private fun showPermissionRationale(
    rootController: RootController,
    permission: String,
    title: String,
    onRationaleDismiss: () -> Unit,
) {
    rootController.findModalController().presentStandardBS {
        ShouldPermissionRationaleBSScreen(
            permission = permission,
            text = title,
            onAcceptClick = {
                onRationaleDismiss()
                rootController.findModalController().popBackStack(null)
            }, onDeclineClick = {
                onRationaleDismiss()
                rootController.findModalController().popBackStack(null)
            })
    }
}