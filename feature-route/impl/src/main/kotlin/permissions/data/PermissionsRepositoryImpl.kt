package permissions.data

import permissions.data.datasource.PermissionsDataSource
import permissions.domain.PermissionsRepository

class PermissionsRepositoryImpl(
    private val dataSource: PermissionsDataSource
) : PermissionsRepository {

    override suspend fun setRationaleDismissed(permission: String) {
        dataSource.setRationaleDismissed(permission)
    }

    override suspend fun isShowRationaleDismissed(permission: String) = dataSource.isShowRationaleDismissed(permission)

    override fun isPermissionGranted(permission: String) = dataSource.isPermissionGranted(permission)
}