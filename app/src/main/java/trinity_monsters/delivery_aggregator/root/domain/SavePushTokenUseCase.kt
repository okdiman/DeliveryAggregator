package trinity_monsters.delivery_aggregator.root.domain

import coroutines.AppDispatchers
import data.AuthLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SavePushTokenUseCase(
    private val coroutineScope: CoroutineScope,
    private val appDispatchers: AppDispatchers,
    private val dataSource: AuthLocalDataSource
) {
    operator fun invoke(token: String) {
        coroutineScope.launch(appDispatchers.storage) {
            dataSource.savePushToken(token)
        }
    }
}