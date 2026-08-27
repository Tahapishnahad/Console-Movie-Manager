//Coded By Tahapishnahad
// tahapishnahad0@gmail.com

sealed class ConsoleMessages {
    fun showErrorMessage(msg: String, additionalInfo: String = ""): String {
        val result = "Console_Error: ❌$msg$additionalInfo"

        return result
    }

    fun showSuccessMessage(msg: String, additionalInfo: String = ""): String {
        val result = "Console_Message: ✅$msg$additionalInfo"

        return result
    }


}
fun printError (error : Any) {
    println(error)
}

fun printSuccess (msg : Any) {
    println(msg)
}
data object ErrorMessages : ConsoleMessages() {
    object MovieErrors {
        val BLANK_TITLE_OR_DIRECTOR_ERROR by lazy { showErrorMessage("Don't enter empty title or director") }
        val BLANK_TITLE_ERROR by lazy{ showErrorMessage("Don't enter empty title")}
        val BLANK_DIRECTOR_ERROR by lazy{ showErrorMessage("Don't enter empty director")}
        val RATING_RANGE_ERROR by lazy{ showErrorMessage("Rating must be in 1 to 10")}
        val ID_RANGE_ERROR by lazy{ showErrorMessage("Id must be in 1 to 10")}
        val SAME_MOVIE_ERROR by lazy{ showErrorMessage("Same movie found . Try entering data with new name and director")}
        val SAME_ID_ERROR by lazy{ showErrorMessage("Same id found . Try entering new data by new ID")}

    }

    object LoginErrors {
        val BLANK_USERNAME_OR_PASSWORD_ERROR by lazy{ showErrorMessage("Don't enter empty username or password")}
        val BLANK_PASSWORD_ERROR by lazy{ showErrorMessage("Don't enter empty password")}
        val PASSWORD_LENGTH_ERROR by lazy{ showErrorMessage("Your password must have at least 8 characters .")}
        val REG_PASSWORD_REPEAT_ERROR by lazy{ showErrorMessage("Your password repeat must match the password")}
        val INCORRECT_LOGIN_USERNAME_OR_PASSWORD_ERROR by lazy{ showErrorMessage("Incorrect username or password")}
        val SAME_PASSWORD_ERROR by lazy{ showErrorMessage("Your input couldn't be same to old password")}
        val MAX_ATTEMPTS_REACHED_ERROR by lazy { showErrorMessage("Maximum attempts reached. ") }
        val USER_DATA_NOT_FOUND_ERROR by lazy { showErrorMessage("User data not found . Please try registering first ") }
    }

    object NothingErrors {

        val MOVIE_NOT_FOUND_ERROR by lazy{ showErrorMessage("Movie not found")}
        val NOTHING_TO_REMOVE_ERROR by lazy{ showErrorMessage("Nothing To Remove")}
        val NOTHING_TO_EDIT_ERROR by lazy{ showErrorMessage("Nothing To Edit")}
        val NOTHING_TO_SHOW_ERROR by lazy{ showErrorMessage("Nothing To Show")}
        val NOTHING_TO_SEARCH_ERROR by lazy{ showErrorMessage("Nothing To Search")}
        val NOTHING_TO_SORT_ERROR by lazy{ showErrorMessage("Nothing To Sort")}
    }

    object MenuErrors {
        val MENU_1_OR_2_ERROR by lazy{ showErrorMessage("Enter 1 or 2")}
        val MENU_1_TO_3_ERROR by lazy{ showErrorMessage("Enter 1 to 3")}
        val MENU_1_TO_10_ERROR by lazy{ showErrorMessage("Enter 1 to 10")}
        val TOO_MANY_ATTEMPTS_ERROR by lazy { showErrorMessage("Too many invalid attempts.") }
        val NON_NUMBER_INPUT_ERROR by lazy { showErrorMessage("Invalid input. Please enter a number.")}
    }


}

data object SuccessMessages : ConsoleMessages() {
    val MOVIE_FOUND by lazy { showSuccessMessage("Movie found") }
    val LOGGING_IN by lazy { showSuccessMessage("True data , LoggingIn ...") }
    val SIGNING_IN by lazy { showSuccessMessage("SigningIn , please wait ...") }
    val LOGGING_OUT by lazy { showSuccessMessage("Logging Out...") }
    val PASSWORD_CHANGED by lazy { showSuccessMessage("Password successfully changed") }

}