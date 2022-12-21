package navigation

object NavigationTree {
    enum class Auth {
        AuthFlow, Login, Offer, Verify
    }

    enum class Registration {
        RegistrationFlow, Company, Bank, Transport, User
    }

    enum class Main {
        MainFlow, Routes, Profile
    }
}