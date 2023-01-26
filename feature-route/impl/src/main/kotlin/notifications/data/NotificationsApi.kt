package notifications.data

import retrofit2.http.GET

interface NotificationsApi {

    @GET("/api/users/notifications")
    suspend fun getNotifications()
}