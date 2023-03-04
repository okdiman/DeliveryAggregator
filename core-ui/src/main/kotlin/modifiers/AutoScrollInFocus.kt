package modifiers

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import kotlinx.coroutines.launch
import utils.keyboard.keyboardAsState

fun Modifier.autoScrollInFocus(
    scrollState: ScrollableState,
    buttonHeight: State<Float>
) = composed {
    var position by remember {
        mutableStateOf(0f)
    }
    /**
     * Какой-то дикий костыль, если не использовать стейт, то onFocusEvent при тапе на textField и открытии клавиатуры
     * выполняется раньше чем onGloballyPositioned в ScrollScreenActionButton, высота кнопки не обновлется и скролл не
     * отрабатывает. В случае со стейтом каким то образом onFocusEvent после открытия клавиатуры отрабатывает повторно
     * и корректно скроллит айтем
     */
    keyboardAsState().apply {
        this.value
    }

    val scope = rememberCoroutineScope()
    onGloballyPositioned {
        position = it.positionInRoot().y
    }.onFocusEvent { state ->
        if (state.isFocused && buttonHeight.value != 0f) {
            scope.launch {
                (position - buttonHeight.value).also { difference ->
                    if (difference > -100f) {
                        scrollState.scrollBy(difference + 100f)
                    }
                }
            }
        }
    }
}