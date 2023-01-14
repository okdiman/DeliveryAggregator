package utils.validators.data

import utils.validators.domain.TextFieldValidator

class LoadCapacityValidator : TextFieldValidator {
    override fun isValidate(value: String) = value.toDoubleOrNull() != null
}