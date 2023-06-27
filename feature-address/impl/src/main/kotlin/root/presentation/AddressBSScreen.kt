package root.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.AddressSuggestUiModel
import root.presentation.model.AddressState
import root.presentation.view.SuggestItemView
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import view.ProgressIndicator
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Suppress("LongMethod")
@Composable
fun AddressBSScreen(
    @StringRes titleRes: Int = R_core.string.common_add_address,
    state: AddressState,
    suggests: List<AddressSuggestUiModel>,
    isNeedComment: Boolean = false,
    onClearClick: () -> Unit,
    onTextFieldChanged: (String) -> Unit,
    onSuggestClick: (AddressSuggestUiModel) -> Unit
) {
    val scrollState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }
    val textFieldValue = remember { mutableStateOf(TextFieldValue(state.stateText, TextRange(state.stateText.length))) }
    LazyColumn(
        state = scrollState,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    ) {
        item {
            AddressTitleView(titleRes)
        }
        item {
            AddressTextFieldView(
                textFieldValue = textFieldValue,
                focusRequester = focusRequester,
                onClearClick = {
                    textFieldValue.value = textFieldValue.value.copy(text = "")
                    onClearClick()
                },
                onTextFieldChanged = { newTextFieldValue ->
                    textFieldValue.value = newTextFieldValue
                    onTextFieldChanged(newTextFieldValue.text)
                }
            )
        }
        if (state.isSuggestLoading) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProgressIndicator()
                }
            }
        } else {
            items(suggests) {
                SuggestItemView(scrollState, it, isNeedComment) { uiModel ->
                    onSuggestClick(uiModel)
                    textFieldValue.value = textFieldValue.value.copy(
                        text = uiModel.subtitle,
                        selection = TextRange(uiModel.subtitle.length)
                    )
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
private fun AddressTitleView(@StringRes titleRes: Int) {
    val rootController = LocalRootController.current
    Row(modifier = Modifier.padding(top = 32.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = titleRes),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        IconButton(
            onClick = { rootController.findModalController().popBackStack(null) }) {
            Icon(
                painter = painterResource(id = R_core.drawable.close_ic),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun AddressTextFieldView(
    textFieldValue: State<TextFieldValue>,
    focusRequester: FocusRequester,
    onClearClick: () -> Unit,
    onTextFieldChanged: (TextFieldValue) -> Unit
) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Theme.colors.selectionTextColor,
        backgroundColor = Theme.colors.selectionTextColor.copy(alpha = 0.5f)
    )
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .focusRequester(focusRequester),
            value = textFieldValue.value,
            textStyle = Theme.fonts.regular.copy(
                platformStyle = null,
                lineHeightStyle = null
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Theme.colors.hintBackgroundColor,
                cursorColor = Theme.colors.textPrimaryColor,
                errorIndicatorColor = Theme.colors.errorColor,
                disabledIndicatorColor = Color.Transparent,
                disabledTrailingIconColor = Color.Black
            ),
            shape = Theme.shapes.textFields,
            trailingIcon = {
                IconButton(onClick = { onClearClick() }) {
                    Icon(
                        painter = painterResource(id = R_core.drawable.delete_ic),
                        contentDescription = null
                    )
                }
            },
            placeholder = {
                Text(
                    text = stringResource(id = R_core.string.common_address_hint),
                    style = Theme.fonts.regular.copy(
                        color = Theme.colors.hintColor
                    )
                )
            },
            onValueChange = { value ->
                onTextFieldChanged(value)
            }
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}