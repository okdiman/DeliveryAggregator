package root.domain

import data.AuthLocalDataSource
import network.domain.GetAuthTokenSyncUseCase

class GetAuthTokenSyncUseCaseImpl(
    private val dataSource: AuthLocalDataSource
) : GetAuthTokenSyncUseCase {
    override fun invoke() = dataSource.getAccessTokenSync()
}