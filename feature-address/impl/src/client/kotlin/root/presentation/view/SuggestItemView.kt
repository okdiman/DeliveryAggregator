package root.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import comment.presentation.AddressCommentParameters
import comment.presentation.compose.AddressCommentScreen
import kotlinx.coroutines.launch
import presentation.AddressSuggestUiModel
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import theme.Theme
import trinity_monsters.delivery_aggregator.core_ui.R
import utils.UiConstants

@Composable
internal fun SuggestItemView(
    scrollState: LazyListState,
    item: AddressSuggestUiModel,
    isNeedComment: Boolean = false,
    onSuggestClick: (AddressSuggestUiModel) -> Unit
) {
    val rootController = LocalRootController.current
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Theme.shapes.textFields)
                .clickable {
                    onSuggestClick(item.copy(isFinal = false))
                    when {
                        item.house.isNotEmpty() && isNeedComment -> {
                            navigateToCommentScreen(rootController, item, onSuggestClick)
                        }
                        item.house.isNotEmpty() -> {
                            rootController
                                .findModalController()
                                .popBackStack(null)
                        }
                        else -> {
                            coroutineScope.launch {
                                scrollState.animateScrollToItem(0)
                            }
                        }
                    }
                }
                .padding(vertical = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.geolocation_ic),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = item.subtitle,
                    style = Theme.fonts.regular
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.city, style = Theme.fonts.regular.copy(
                        fontSize = 14.sp,
                        color = Theme.colors.textFourthColor
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Divider(
            modifier = Modifier.padding(start = 36.dp),
            color = Theme.colors.dividerColor
        )
        Spacer(modifier = Modifier.height(6.dp))
    }
}

private fun navigateToCommentScreen(
    rootController: RootController,
    item: AddressSuggestUiModel,
    onSuggestClick: (AddressSuggestUiModel) -> Unit
) {
    rootController
        .findModalController()
        .present(
            modalSheetConfiguration = ModalSheetConfiguration(
                cornerRadius = UiConstants.BottomSheet.SCREEN_CORNER_RADIUS,
                maxHeight = UiConstants.BottomSheet.SCREEN_MAX_HEIGHT
            )
        ) {
            AddressCommentScreen(
                parameters = AddressCommentParameters(
                    uiModel = item,
                    onSuggestClick = onSuggestClick
                )
            )
        }
}