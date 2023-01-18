package root.domain

import root.domain.model.RouteModel

interface RouteRepository {
    suspend fun getActiveRoute(): RouteModel
}