package utils.validators.data

import utils.validators.domain.TextFieldValidator

class DigitsAndLettersValidator : TextFieldValidator {
    override fun isValidate(value: String): Boolean {
        for (char in value) {
            if (!char.isLetterOrDigit()) {
                return false
            }
        }
        return true
    }
}