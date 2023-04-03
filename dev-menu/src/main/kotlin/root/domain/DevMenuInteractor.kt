package root.domain

import devmenu.domain.DevMenuRepository
import domain.AuthRepository

class DevMenuInteractor(
    private val authRepository: AuthRepository,
    private val devMenuRepository: DevMenuRepository
) {
    suspend fun getServerUrl() = devMenuRepository.getServerUrl()
    suspend fun setServerUrl(url: String) {
        devMenuRepository.setServerUrl(url)
        authRepository.clearToken()
    }

    fun getAvailableHosts() = devMenuRepository.getAvailableHosts()
}