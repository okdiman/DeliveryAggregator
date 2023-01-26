package network.state.domain

import kotlinx.coroutines.flow.Flow

interface NetworkStateRepository {
    fun isInternetOn(): Boolean
    fun isNetworkAvailableFlow(): Flow<Boolean>
}