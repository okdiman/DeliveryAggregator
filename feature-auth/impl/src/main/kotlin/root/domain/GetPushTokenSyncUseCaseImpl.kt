package root.domain

import data.AuthLocalDataSource
import network.domain.GetPushTokenSyncUseCase

class GetPushTokenSyncUseCaseImpl(
    private val dataSource: AuthLocalDataSource
) : GetPushTokenSyncUseCase {
    override fun invoke() = dataSource.getPushTokenSync()
}