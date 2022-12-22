package utils

import android.util.Patterns
import java.util.regex.Pattern

fun isTextFieldFilled(newText: String, minChar: Int) = newText.length >= minChar

fun isEmailCorrect(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun isStringFormatCorrect(newValue: String, regex: String) =
    Pattern.compile(regex).matcher(newValue).matches() || newValue.isEmpty()