package root

object RegistrationConstants {
    object Step {
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3
        const val FOUR = 4
    }

    object Limits {
        const val INN_CHARS = 10
        const val KPP_CHARS = 9
        const val OGRN_CHARS = 13
        const val MIN_ADDRESS_CHARS = 5
        const val MIN_NAME_CHARS = 2
        const val MAX_NAME_CHARS = 50
        const val BANK_ACC_CHARS = 20
        const val BIK_CHARS = 9
        const val LICENCE_PLATE_MIN_CHARS = 8
        const val LICENCE_PLATE_MAX_CHARS = 9
        const val CAR_BRAND_MIN_CHARS = 2
        const val CAR_INFO_MIN_CHARS = 1
        const val CAR_BRAND_MAX_CHARS = 20
        const val CAR_CATEGORY_MAX_CHARS = 10
        const val CAR_CAPACITY_MAX_CHARS = 5
    }

    const val HINT_ALPHA = 0.7f
}