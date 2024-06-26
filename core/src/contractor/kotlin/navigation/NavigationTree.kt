package navigation

object NavigationTree {

    enum class Splash {
        Splash
    }

    enum class Auth {
        AuthFlow, Login, Pdf, Verify
    }

    enum class Registration {
        RegistrationFlow, Company, Bank, Transport, User
    }

    enum class Main {
        MainFlow, Routes, Profile, DevMenu
    }

    enum class Profile {
        Pdf, Edit, DepartureAddress, Transport
    }

    enum class Routes {
        Notifications, RouteDetails, LoadingState, DeliveryState
    }
}