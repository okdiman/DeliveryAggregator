package utils.validators

import java.util.regex.Pattern
import utils.CommonConstants.REGEX.LICENCE_PLATE

class LicencePlateValidator : TextFieldValidator {
    override fun isValidate(value: String) = Pattern.compile(LICENCE_PLATE).matcher(value).matches()
}