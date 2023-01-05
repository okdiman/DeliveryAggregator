package utils.validators

import java.util.regex.Pattern
import utils.CommonConstants.REGEX.LETTERS

class LettersValidator : TextFieldValidator {
    override fun isValidate(value: String) = Pattern.compile(LETTERS).matcher(value).matches()
}