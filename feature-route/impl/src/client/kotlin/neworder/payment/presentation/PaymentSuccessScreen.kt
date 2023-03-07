package neworder.payment.presentation

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
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R

@Composable
fun PaymentSuccessScreen(parameters: PaymentSuccessParameters) {
    val rootController = LocalRootController.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.new_order_paid_ic),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.new_order_payment_title),
            style = Theme.fonts.bold.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center
        )
        if (parameters.price != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 36.dp),
                text = parameters.price,
                textAlign = TextAlign.Center,
                style = Theme.fonts.regular
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        ActionButton(
            textRes = R.string.new_order_payment_button,
            alignment = Alignment.TopCenter
        ) { navigateToOrders(rootController) }
    }
    BackHandler { navigateToOrders(rootController) }
}

private fun navigateToOrders(rootController: RootController) {
    rootController.popBackStack()
}