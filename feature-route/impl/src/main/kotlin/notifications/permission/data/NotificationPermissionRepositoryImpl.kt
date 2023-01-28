package notifications.permission.data

import notifications.permission.data.datasource.NotificationPermissionDataSource
import notifications.permission.domain.NotificationPermissionRepository

class NotificationPermissionRepositoryImpl(
    private val dataSource: NotificationPermissionDataSource
) : NotificationPermissionRepository {

    override suspend fun setRationaleDismissed() {
        dataSource.setRationaleDismissed()
    }

    override suspend fun isShowRationaleDismissed() = dataSource.isShowRationaleDismissed()
}