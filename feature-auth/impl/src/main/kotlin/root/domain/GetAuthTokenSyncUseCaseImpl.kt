package root.domain

import data.datasource.AuthLocalDataSource
import network.domain.GetAuthTokenSyncUseCase

class GetAuthTokenSyncUseCaseImpl(
    private val dataSource: AuthLocalDataSource
) : GetAuthTokenSyncUseCase {
    override fun invoke() = dataSource.getTokenSync().orEmpty()
}