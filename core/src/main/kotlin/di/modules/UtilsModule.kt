package di.modules

import com.google.gson.Gson
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import utils.validators.data.DigitsAndLettersValidator
import utils.validators.data.EmailValidator
import utils.validators.data.LettersValidator
import utils.validators.data.LicencePlateValidator
import utils.validators.data.LoadCapacityValidator
import utils.validators.data.NamingValidator
import utils.validators.domain.TextFieldValidator

const val DIGITS_AND_LETTERS_VALIDATOR_QUALIFIER = "digits_and_letters"
const val LETTERS_VALIDATOR_QUALIFIER = "letters"
const val LICENCE_PLATE_VALIDATOR_QUALIFIER = "licence_plate"
const val NAMING_VALIDATOR_QUALIFIER = "naming"
const val EMAIL_VALIDATOR_QUALIFIER = "email"
const val LOAD_CAPACITY_VALIDATOR_QUALIFIER = "load_capacity"

internal fun utilsModule() = module {
    factory { Gson() }
    factory(named(DIGITS_AND_LETTERS_VALIDATOR_QUALIFIER)) {
        DigitsAndLettersValidator()
    } bind TextFieldValidator::class
    factory(named(LETTERS_VALIDATOR_QUALIFIER)) {
        LettersValidator()
    } bind TextFieldValidator::class
    factory(named(LICENCE_PLATE_VALIDATOR_QUALIFIER)) {
        LicencePlateValidator()
    } bind TextFieldValidator::class
    factory(named(NAMING_VALIDATOR_QUALIFIER)) {
        NamingValidator()
    } bind TextFieldValidator::class
    factory(named(EMAIL_VALIDATOR_QUALIFIER)) {
        EmailValidator()
    } bind TextFieldValidator::class
    factory(named(LOAD_CAPACITY_VALIDATOR_QUALIFIER)) {
        LoadCapacityValidator()
    } bind TextFieldValidator::class
}