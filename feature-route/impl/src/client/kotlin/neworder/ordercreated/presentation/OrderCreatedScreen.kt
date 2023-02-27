package neworder.ordercreated.presentation

import ActionButton
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
fun OrderCreatedScreen(parameters: OrderCreatedParameters) {
    val rootController = LocalRootController.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.new_order_created),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.new_order_created),
            style = Theme.fonts.bold.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 52.dp),
            text = parameters.date,
            style = Theme.fonts.bold.copy(
                color = Theme.colors.textPrimaryColor.copy(alpha = 0.6f)
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        ActionButton(
            textRes = R_core.string.common_good,
            alignment = Alignment.TopCenter
        ) { navigateToRoutesScreen(rootController) }
    }
    BackHandler {
        navigateToRoutesScreen(rootController)
    }
}

private fun navigateToRoutesScreen(rootController: RootController) {
    rootController.findRootController().present(
        screen = NavigationTree.Main.MainFlow.name,
        launchFlag = LaunchFlag.SingleInstance,
        startTabPosition = CommonConstants.BottomNavigation.ROUTE_TAB
    )
}