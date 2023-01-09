package di.modules

import com.google.gson.Gson
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import utils.validators.DigitsAndLettersValidator
import utils.validators.EmailValidator
import utils.validators.LettersValidator
import utils.validators.LicencePlateValidator
import utils.validators.NamingValidator
import utils.validators.TextFieldValidator

const val DIGITS_AND_LETTERS_VALIDATOR_QUALIFIER = "digits_and_letters"
const val LETTERS_VALIDATOR_QUALIFIER = "letters"
const val LICENCE_PLATE_VALIDATOR_QUALIFIER = "licence_plate"
const val NAMING_VALIDATOR_QUALIFIER = "naming"
const val EMAIL_VALIDATOR_QUALIFIER = "email"

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
}