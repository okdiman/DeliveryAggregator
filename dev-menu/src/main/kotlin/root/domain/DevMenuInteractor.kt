package root.domain

import devmenu.domain.DevMenuRepository

class DevMenuInteractor(
    private val repository: DevMenuRepository
) {
    suspend fun getServerUrl() = repository.getServerUrl()
    suspend fun setServerUrl(url: String) = repository.setServerUrl(url)
    fun getAvailableHosts() = repository.getAvailableHosts()
}