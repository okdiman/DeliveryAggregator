package utils

import android.util.Patterns

fun isTextFieldFilled(newText: String, minChar: Int) = newText.length >= minChar

fun isEmailCorrect(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}