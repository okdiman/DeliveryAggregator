package utils

object CommonConstants {
    object MASK {
        const val PHONE = "(000) 000-00-00"
        const val CODE = "00-00"
    }

    object REGEX {
        const val NAMES = "[А-Яа-яA-Za-z-]+"
        const val LICENCE_PLATE = "[А-Яа-я][0-9]{3}[А-Яа-я]{2}[0-9]+"
    }

    object LIMITS {
        object Transport {
            const val LICENCE_PLATE_MIN_CHARS = 8
            const val LICENCE_PLATE_MAX_CHARS = 9
            const val CAR_BRAND_MIN_CHARS = 2
            const val CAR_BRAND_MAX_CHARS = 20
            const val CAR_INFO_MIN_CHARS = 1
            const val CAR_CATEGORY_MAX_CHARS = 10
            const val CAR_CAPACITY_MAX_CHARS = 5
        }

        object User {
            const val MAX_USER_NAME_CHARS = 30
        }

        object Common {
            const val MAX_PHONE_CHARS = 10
            const val MIN_ADDRESS_CHARS = 5
            const val MIN_NAME_CHARS = 2
            const val MAX_NAME_CHARS = 50
        }
    }

    object Helpers {
        const val LINE_BREAK = "\n"
        const val SPACER = " "
    }

    object System {
        const val APP_PACKAGE = "app_package"
        const val APP_UID = "app_uid"
    }

    object BottomNavigation {
        const val ROUTE_TAB = 0
        const val PROFILE_TAB = 1
    }
}