package presentation.compose

import CommonErrorScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import presentation.viewmodel.model.SplashEvent
import presentation.viewmodel.model.SplashState
import trinity_monsters.delivery_aggregator.core_ui.R

import view.ProgressIndicator

@Composable
fun SplashView(state: SplashState, eventHandler: (SplashEvent) -> Unit) {
    if (state.isError) {
        CommonErrorScreen { eventHandler(SplashEvent.OnRetryClick) }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(300.dp),
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(24.dp))
            ProgressIndicator()
        }
    }
}