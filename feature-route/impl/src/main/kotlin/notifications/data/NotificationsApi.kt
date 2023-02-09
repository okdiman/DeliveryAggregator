package notifications.data

import notifications.data.model.NotificationsDto
import notifications.data.model.UnreadNotificationsDto
import notifications.data.model.response.NotificationsMarkedDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotificationsApi {

    @GET("/api/users/notifications")
    suspend fun getNotifications(): NotificationsDto

    @GET("/api/users/notifications/list/unread")
    suspend fun getUnreadNotificationsCount(): UnreadNotificationsDto

    @POST("/api/users/notifications/read")
    suspend fun markNotificationsAsRead(@Body notifications: NotificationsMarkedDto)
}