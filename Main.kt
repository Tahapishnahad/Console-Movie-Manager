//Coded by Tahapishnahad
// tahapishnahad0@gmail.com

fun main() {

    if (!MainObject.dataFile.exists()) {
        MainObject.dataFile.createNewFile()
    }

    var attempts = 0
    val maxAttempts = 3
    while (attempts < maxAttempts) {

        if (MainObject.dataFile.readLines().isNotEmpty()) {
            val result = MainObject.registerAndLoginObject.getLoginData()
            if (AuthUtilities.isValidLogin(result)) {
                printSuccess(SuccessMessages.LOGGING_IN)
                break
            }
            attempts++

        } else {
            val result = MainObject.registerAndLoginObject.getRegisterData()
            val passwordInput = result.first.password
            val usernameInput = result.first.username
            val encryptedPassword = MainObject.encryptionObject.encrypt(passwordInput)
            val encryptedUsername = MainObject.encryptionObject.encrypt(usernameInput)

            if (AuthUtilities.isValidRegister(result.first, result.second)) {

                val dataToSave = """
        ${encryptedUsername.first}
        ${encryptedUsername.second}
        ${encryptedPassword.first}
        ${encryptedPassword.second}
    """.trimIndent()
                MainObject.dataFile.writeText(dataToSave)
                printSuccess(SuccessMessages.SIGNING_IN)
                break
            }
            attempts++

        }

        if (attempts == maxAttempts) {
            printError(ErrorMessages.LoginErrors.MAX_ATTEMPTS_REACHED_ERROR)
            return
        }


    }
    while (true) {
        MainObject.printMenu()

        when (MainObject.getMenuData()) {

            1 -> {
                MainObject.movieObject.addMovie()

            }

            2 -> {
                MainObject.movieObject.removeMovie()

            }

            3 -> {
                MainObject.movieObject.editMovie()

            }

            4 -> {
                MainObject.movieObject.showAllMovies()

            }

            5 -> {
                MainObject.movieObject.searchMovie()

            }

            6 -> {
                MainObject.movieObject.sortMoviesByRating()

            }

            7 -> {
                MainObject.registerAndLoginObject.changePassword()

            }

            8 -> {
                MainObject.registerAndLoginObject.logOut()
                break
            }

            9 -> {
                println("Exit ...")
                break
            }

            10 -> {
                MainObject.showAbout()

            }


            else -> printError(ErrorMessages.MenuErrors.MENU_1_TO_10_ERROR)

        }

    }


}