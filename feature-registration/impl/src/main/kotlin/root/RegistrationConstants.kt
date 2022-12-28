package root

object RegistrationConstants {
    object Step {
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3
        const val FOUR = 4
    }

    object Limits {
        object Company {
            const val INN_CHARS = 12
            const val INN_MIN_CHARS = 10
            const val KPP_CHARS = 9
            const val OGRN_CHARS = 13
        }

        object Bank {
            const val BANK_ACC_CHARS = 20
            const val BIK_CHARS = 9
        }
    }
}