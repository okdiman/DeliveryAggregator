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
        MainFlow, Orders, Profile, DevMenu
    }

    enum class NewOrder {
        Creating, Storages, OrderCreated, CreationError, PaymentSuccess
    }

    enum class Profile {
        Offer, Edit, DepartureAddress, Transport
    }

    enum class Routes {
        Notifications, Details, ConfirmChanges
    }
}
