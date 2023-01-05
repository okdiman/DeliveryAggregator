package utils.validators

class NamingValidator : TextFieldValidator {
    override fun isValidate(value: String): Boolean {
        for (char in value) {
            if (!char.isLetter() && isNotPermittedSymbol(char)) {
                return false
            }
        }
        return true
    }

    private fun isNotPermittedSymbol(char: Char): Boolean {
        return char != '_' && char != '.' && char != '-'
    }
}