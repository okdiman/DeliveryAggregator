package network.state.domain

class NetworkStateInteractor(
    private val repository: NetworkStateRepository
) : NetworkStateRepository by repository