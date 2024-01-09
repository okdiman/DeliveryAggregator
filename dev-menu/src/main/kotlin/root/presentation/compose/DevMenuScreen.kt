package root.presentation.compose

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.compose.observeAsState
import root.presentation.viewmodel.DevMenuViewModel
import root.presentation.viewmodel.model.DevMenuAction
import theme.DeliveryAggregatorTheme

@Composable
fun DevMenuScreen() {
    ViewModel(factory = { DevMenuViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()
        DevMenuView(state.value) { event ->
            viewModel.obtainEvent(event)
        }
        when (action.value) {
            is DevMenuAction.ShowToast -> {
                Toast.makeText(LocalContext.current, action.value?.text, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
private fun DevMenuScreen_Preview() {
    DeliveryAggregatorTheme {
        DevMenuScreen()
    }
}
