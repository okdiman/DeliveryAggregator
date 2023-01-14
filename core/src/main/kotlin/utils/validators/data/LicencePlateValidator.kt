package utils.validators.data

import java.util.regex.Pattern
import utils.CommonConstants.REGEX.LICENCE_PLATE
import utils.validators.domain.TextFieldValidator

class LicencePlateValidator : TextFieldValidator {
    override fun isValidate(value: String) = Pattern.compile(LICENCE_PLATE).matcher(value).matches()
}