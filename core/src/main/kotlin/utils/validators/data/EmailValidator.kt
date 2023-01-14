package utils.validators.data

import android.util.Patterns
import utils.validators.domain.TextFieldValidator

class EmailValidator : TextFieldValidator {
    override fun isValidate(value: String) = Patterns.EMAIL_ADDRESS.matcher(value).matches()
}