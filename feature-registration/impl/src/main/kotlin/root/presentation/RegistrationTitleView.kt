package root.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import root.RegistrationConstants.Step.FOUR
import root.RegistrationConstants.Step.THREE
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_registration.impl.BuildConfig
import trinity_monsters.delivery_aggregator.feature_registration.impl.R
import utils.CommonConstants
import view.BackButtonView

@Composable
fun RegistrationTitleView(
    isBackButtonVisible: Boolean = false,
    onButtonClick: () -> Unit = {},
    step: Int,
    @DrawableRes imageRes: Int,
    @StringRes titleRes: Int
) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(top =  12.dp)
    ) {
        if (isBackButtonVisible) {
            BackButtonView { onButtonClick() }
        }
        val stepCount = if (BuildConfig.FLAVOR == CommonConstants.Flavors.client) {
            THREE
        } else {
            FOUR
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = String.format(
                stringResource(R.string.registration_step),
                step,
                stepCount
            ),
            style = Theme.fonts.bold.copy(fontSize = 20.sp)
        )
    }
    Spacer(Modifier.height(30.dp))
    Image(
        painter = painterResource(imageRes),
        contentDescription = null
    )
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        text = stringResource(titleRes),
        style = Theme.fonts.bold.copy(fontSize = 24.sp)
    )
}