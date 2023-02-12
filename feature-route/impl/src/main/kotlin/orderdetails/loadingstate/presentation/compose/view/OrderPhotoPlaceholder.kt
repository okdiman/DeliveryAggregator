package orderdetails.loadingstate.presentation.compose.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import camera.PhotoFileProvider
import permissions.AppPermissionState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R

@Composable
internal fun OrderPhotoPlaceholder(
    permissionState: AppPermissionState?,
    onPhotoAdded: (Uri) -> Unit,
    onPhotoClick: () -> Unit
) {
    val context = LocalContext.current
    val uri = PhotoFileProvider.getImageUri(context)
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            onPhotoAdded(uri)
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(Theme.shapes.roundedButton)
            .background(Theme.colors.disabledButtonColor)
            .clickable {
                if (permissionState == AppPermissionState.Granted) {
                    cameraLauncher.launch(uri)
                } else {
                    onPhotoClick()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.route_state_camera_ic),
            contentDescription = null
        )
    }
}