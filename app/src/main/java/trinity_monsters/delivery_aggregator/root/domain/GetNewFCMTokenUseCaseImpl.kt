package trinity_monsters.delivery_aggregator.root.domain

import com.google.firebase.messaging.FirebaseMessaging
import domain.usecase.notifications.GetNewFCMTokenUseCase

class GetNewFCMTokenUseCaseImpl(
    private val firebaseMessaging: FirebaseMessaging,
    private val savePushToken: SavePushTokenUseCase
) : GetNewFCMTokenUseCase {
    override fun invoke() {
        firebaseMessaging.deleteToken().addOnSuccessListener {
            FirebaseMessaging.getInstance().token.addOnSuccessListener {
                savePushToken(it)
            }
        }
    }
}