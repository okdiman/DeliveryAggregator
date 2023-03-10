package navigation

object NavigationTree {

    enum class Splash {
        Splash
    }

    enum class Auth {
        AuthFlow, Login, Offer, Verify
    }

    enum class Registration {
        RegistrationFlow, Company, Bank, User
    }

    enum class Main {
        MainFlow, Orders, Profile
    }

    enum class NewOrder {
        Creating, Storages, OrderCreated
    }

    enum class Profile {
        Offer, Edit, DepartureAddress, Transport
    }

    enum class Routes {
        Notifications, RouteDetails, LoadingState, DeliveryState, ConfirmChanges
    }
}
