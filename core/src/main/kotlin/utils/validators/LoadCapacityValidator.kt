package utils.validators

class LoadCapacityValidator : TextFieldValidator {
    override fun isValidate(value: String) = value.toDoubleOrNull() != null
}