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
        const val MAX_PHONE_CHARS = 10
    }

    const val APP_PACKAGE = "app_package"
    const val APP_UID = "app_uid"
}