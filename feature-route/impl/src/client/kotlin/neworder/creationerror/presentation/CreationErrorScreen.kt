package neworder.creationerror.presentation

import ActionButton
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navigation.BottomNavConstants
import navigation.NavigationTree
import openBrowser
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R

@Composable
fun CreationErrorScreen(parameters: CreationErrorParameters) {
    val rootController = LocalRootController.current
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.new_order_creation_error),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.new_order_creation_text),
            style = Theme.fonts.bold.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        ActionButton(
            textRes = R.string.new_order_creation_button,
            alignment = Alignment.TopCenter
        ) { openBrowser(context, parameters.uri) }
    }
    BackHandler {
        rootController.findRootController().present(
            screen = NavigationTree.Main.MainFlow.name,
            launchFlag = LaunchFlag.SingleInstance,
            startTabPosition = BottomNavConstants.ROUTE_TAB
        )
    }
}