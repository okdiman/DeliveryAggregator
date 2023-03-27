package utils

import ru.alexgladkov.odyssey.compose.Render
import ru.alexgladkov.odyssey.compose.controllers.ModalController
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration

fun ModalController.presentStandardBS(
    maxHeight: Float? = null,
    cornerRadius: Int = UiConstants.BottomSheet.SCREEN_CORNER_RADIUS,
    content: Render
) {
    present(
        modalSheetConfiguration = ModalSheetConfiguration(
            cornerRadius = cornerRadius,
            maxHeight = maxHeight
        ), content
    )
}