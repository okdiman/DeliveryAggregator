package navigation

object NavigationTree {

    enum class Splash {
        Splash
    }

    enum class Auth {
        AuthFlow, Login, Offer, Verify
    }

    enum class Registration {
        RegistrationFlow, Company, Bank, Transport, User
    }

    enum class Main {
        MainFlow, Routes, Profile
    }

    enum class Profile {
        Offer, Edit, DepartureAddress, Transport
    }
}