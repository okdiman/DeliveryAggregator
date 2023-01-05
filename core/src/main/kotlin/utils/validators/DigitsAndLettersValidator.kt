package utils.validators

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