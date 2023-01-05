package utils.validators

import android.util.Patterns

class EmailValidator : TextFieldValidator {
    override fun isValidate(value: String) = Patterns.EMAIL_ADDRESS.matcher(value).matches()
}