package orderdetails.loadingstate.presentation.compose

import ScrollScreenActionButton
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import camera.PhotoFileProvider
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingEvent
import orderdetails.loadingstate.presentation.viewmodel.model.OrderLoadingState
import permissions.AppPermissionState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants.LIMITS.Transport.CAR_CAPACITY_MAX_CHARS
import view.BackButton
import view.StandardTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun OrderLoadingView(state: OrderLoadingState, eventHandler: (OrderLoadingEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            OrderLoadingTitle(eventHandler)
        }
        item {
            OrderLoadingPhotoHintView()
        }
        item {
            if (state.photo == null) {
                OrderLoadingPhotoPlaceholder(state, eventHandler)
            } else {
                OrderLoadingPhotoView(state)
            }
        }
        item {
            OrderLoadingSpecifyInfoView()
        }
        item {
            OrderLoadingTextFieldBlock(state, eventHandler)
        }
    }
    if (state.isDoneButtonVisible) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ScrollScreenActionButton(
                textRes = R.string.loading_step_done
            ) { eventHandler(OrderLoadingEvent.OnDoneButtonClick) }
        }
    }
}

@Composable
private fun OrderLoadingTitle(eventHandler: (OrderLoadingEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        BackButton(modifier = Modifier.padding(top = 3.dp)) {
            eventHandler(OrderLoadingEvent.OnBackClick)
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.loading),
            style = Theme.fonts.bold.copy(fontSize = 20.sp)
        )
    }
}

@Composable
private fun OrderLoadingPhotoHintView() {
    Text(
        modifier = Modifier.padding(top = 24.dp),
        text = stringResource(id = R.string.loading_hint), style = Theme.fonts.regular
    )
}

@Composable
private fun OrderLoadingPhotoPlaceholder(state: OrderLoadingState, eventHandler: (OrderLoadingEvent) -> Unit) {
    val context = LocalContext.current
    val uri = PhotoFileProvider.getImageUri(context)
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            eventHandler(OrderLoadingEvent.OnPhotoAdded(uri))
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(Theme.shapes.roundedButton)
            .background(Theme.colors.disabledButtonColor)
            .clickable {
                if (state.permissionState == AppPermissionState.Granted) {
                    cameraLauncher.launch(uri)
                } else {
                    eventHandler(OrderLoadingEvent.OnPhotoClick)
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

@Composable
fun OrderLoadingPhotoView(state: OrderLoadingState) {
    Spacer(modifier = Modifier.height(24.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Theme.shapes.card),
        backgroundColor = Theme.colors.hintBackgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(78.dp)
                    .clip(Theme.shapes.photo),
                painter = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(data = state.photo?.uri)
                        .build()
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.loading_step), style = Theme.fonts.bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = state.photo?.date ?: "", style = Theme.fonts.regular.copy(
                        color = Theme.colors.textPrimaryColor.copy(alpha = 0.7f),
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun OrderLoadingSpecifyInfoView() {
    Text(
        modifier = Modifier.padding(top = 24.dp),
        text = stringResource(id = R.string.loading_specify_info),
        style = Theme.fonts.bold.copy(
            fontSize = 20.sp
        )
    )
}

@Composable
private fun OrderLoadingTextFieldBlock(
    state: OrderLoadingState,
    eventHandler: (OrderLoadingEvent) -> Unit
) {
    StandardTextField(
        title = stringResource(R.string.loading_boxes_count),
        state = state.boxesCount,
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        hint = stringResource(R.string.loading_count_hint),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(OrderLoadingEvent.OnBoxesCountChanged(it)) }
    StandardTextField(
        title = stringResource(R.string.loading_pallets_count),
        state = state.palletsCount,
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        hint = stringResource(R.string.loading_count_hint),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(OrderLoadingEvent.OnPalletsCountChanged(it)) }
    StandardTextField(
        modifier = Modifier.clickable {
            eventHandler(OrderLoadingEvent.OnOpenCargoTypeBSClick)
        },
        title = stringResource(R.string.loading_cargo_type),
        state = state.cargoType,
        hint = stringResource(R.string.loading_choose),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = trinity_monsters.delivery_aggregator.core_ui.R.drawable.chevron_ic),
                contentDescription = null
            )
        }
    )
    StandardTextField(
        modifier = Modifier.clickable {
            eventHandler(OrderLoadingEvent.OnOpenAdditionalOptBSClick)
        },
        title = stringResource(R.string.loading_add_info),
        state = state.additionalOptions,
        hint = stringResource(R.string.loading_choose),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        }
    )
    if (state.isDoneButtonVisible) {
        Spacer(modifier = Modifier.height(120.dp))
    }
}