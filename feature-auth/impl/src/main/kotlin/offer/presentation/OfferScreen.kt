package offer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_auth.impl.R

@Composable
fun OfferScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.back_ic), contentDescription = "")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.offer),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Theme.colors.textPrimaryColor
        )
        Text(
            text = "Настоящий документ (далее – Политика) определяет цели и общие принципы обработки персональных данных, " +
                    "а также реализуемые меры защиты персональных данных. Политика является общедоступным документом оператора " +
                    "и предусматривает возможность ознакомления с ней любых лиц. Политика действует бессрочно после утверждения " +
                    "и до ее замены новой версией. В Политике используются термины и определения в соответствии с их значениями, " +
                    "как они определены в ФЗ-152 \"О персональных данных Обработка персональных данных Оператора осуществляется " +
                    "с соблюдением принципов и условий, предусмотренных настоящей Политикой и законодательством Российской Федерации " +
                    "в области персональных данных",
            fontSize = 16.sp,
            color = Theme.colors.textPrimaryColor
        )
    }
}