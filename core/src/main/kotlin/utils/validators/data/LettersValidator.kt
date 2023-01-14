package utils.validators.data

import java.util.regex.Pattern
import utils.CommonConstants.REGEX.LETTERS
import utils.validators.domain.TextFieldValidator

class LettersValidator : TextFieldValidator {
    override fun isValidate(value: String) = Pattern.compile(LETTERS).matcher(value).matches()
}