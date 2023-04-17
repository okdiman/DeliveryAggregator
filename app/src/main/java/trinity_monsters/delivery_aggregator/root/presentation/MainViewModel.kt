package trinity_monsters.delivery_aggregator.root.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel
import domain.usecase.IsAuthorizedUseCase
import navigation.NavigationTree
import notifications.NotificationsConstant.DataKeys.DESTINATION
import utils.ext.getSafeQueryParameter

class MainViewModel(
    private val isAuthorized: IsAuthorizedUseCase
) : ViewModel() {
    suspend fun getStartDeeplinkDestination(intent: Intent?): String? {
        val destination = intent?.data?.getSafeQueryParameter(DESTINATION)
        return when {
            destination != null && isAuthorized() -> NavigationTree.Main.MainFlow.name
            destination != null -> NavigationTree.Auth.AuthFlow.name
            else -> null
        }
    }
}
