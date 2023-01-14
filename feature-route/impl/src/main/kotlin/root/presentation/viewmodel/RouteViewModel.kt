package root.presentation.viewmodel

import BaseViewModel
import root.presentation.viewmodel.model.RouteAction
import root.presentation.viewmodel.model.RouteEvent
import root.presentation.viewmodel.model.RouteState

class RouteViewModel : BaseViewModel<RouteState, RouteAction, RouteEvent>(
    initialState = RouteState()
) {
    override fun obtainEvent(viewEvent: RouteEvent) {
        when (viewEvent) {
            is RouteEvent.AcceptOrderClick -> onAcceptOrderClick(viewEvent.id)
            is RouteEvent.OnRouteClick -> onRouteClick(viewEvent.id)
            RouteEvent.OnNotificationsClick -> onNotificationsClick()
            RouteEvent.ResetAction -> onResetAction()
        }
    }

    private fun onAcceptOrderClick(id: String) {
        launchJob {
            //TODO
            viewAction = RouteAction.OpenRouteDetail(id)
        }
    }

    private fun onRouteClick(id: String) {
        viewAction = RouteAction.OpenRouteDetail(id)
    }

    private fun onNotificationsClick() {
        viewAction = RouteAction.OpenNotifications
    }
}