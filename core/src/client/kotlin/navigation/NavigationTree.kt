package navigation

object NavigationTree {

    enum class Splash {
        Splash
    }

    enum class Auth {
        AuthFlow, Login, Pdf, Verify
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
        Pdf, Edit, DepartureAddress, Transport
    }

    enum class Routes {
        Notifications, Details, ConfirmChanges
    }
}