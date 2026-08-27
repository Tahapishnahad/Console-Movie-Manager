//Coded by Tahapishnahad
// tahapishnahad0@gmail.com

object MovieUtilities {

    fun isValidMovie(movie: Movie, movies: List<Movie>): Boolean {
        if (movie.title.isBlank() || movie.director.isBlank()) {
            printError(ErrorMessages.MovieErrors.BLANK_TITLE_OR_DIRECTOR_ERROR)
            return false
        }

        if (movie.rating !in MainObject.MIN_ID_AND_RATE..MainObject.MAX_RATE) {
            printError(ErrorMessages.MovieErrors.RATING_RANGE_ERROR)
            return false
        }

        if (movie.id !in MainObject.MIN_ID_AND_RATE..MainObject.MAX_ID) {
            printError(ErrorMessages.MovieErrors.ID_RANGE_ERROR)
            return false
        }

        if (movies.find { it.director == movie.director && it.title == movie.title } != null) {
            printError(ErrorMessages.MovieErrors.SAME_MOVIE_ERROR)
            return false
        }

        if (movies.find { it.id == movie.id } != null) {
            printError(ErrorMessages.MovieErrors.SAME_ID_ERROR)
            return false
        }

        return true
    }

    fun findMovieById(id: Int, movies: List<Movie>): Movie? {
        return movies.find { it.id == id }
    }

    fun findMovieByTitle(title: String, movies: List<Movie>): Movie? {
        return movies.find { it.title.contains(title) }
    }

    fun searchMovieByTitle(title: String, movies: List<Movie>): List<Movie> {
        return movies.filter { it.title.contains(title) }
    }

    fun printMovie(movie: Movie) {
        println("Movie(s): ")
        movie.run {
            println(
                """
                    
                📝 Movie Title: $title 
                👥 Movie Director: $director
                ⭐ Movie Rate: $rating
                🆔 Movie ID: $id
              
            """.trimIndent()
            )
            println("=".repeat(40))
        }
    }

    fun editSwitcher(result: Movie, movies: List<Movie>) {
        when (getEditMenu()) {
            1 -> {
                println("-- Edit Title --")
                print("Enter New Title: ")
                val titleEditInput = readln()

                if (isValidTitle(titleEditInput, result, movies)) {
                    printSuccess(
                        SuccessMessages.showSuccessMessage(
                            "Movie title Updated. ",
                            "old Title: ${result.title}, new Title: $titleEditInput"
                        )
                    )
                    result.title = titleEditInput
                }
            }

            2 -> {
                println("-- Edit Director --")
                print("Enter New Director: ")
                val directorEditInput = readln()

                if (isValidDirector(directorEditInput, result)) {
                    printSuccess(
                        SuccessMessages.showSuccessMessage(
                            "Movie director Updated.",
                            "old director: ${result.director}, new director: $directorEditInput"
                        )
                    )
                    result.director = directorEditInput
                }
            }

            3 -> {
                println("-- Edit Rate --")
                val ratingEditInput = MainObject.readIntSafely("Enter New Rate :") ?: return

                if (isValidRate(ratingEditInput, result)) {
                    printSuccess(
                        SuccessMessages.showSuccessMessage(
                            "Movie rating Updated.",
                            "old Rating: ${result.rating}, new Rating: $ratingEditInput"
                        )
                    )
                    result.rating = ratingEditInput
                }
            }
        }
    }

    private fun isTitleExist(title: String, movies: List<Movie>): Boolean {
        var found = false
        movies.forEach {
            if (it.title == title)
                found = true
        }
        return found
    }

    private fun isValidTitle(title: String, movie: Movie, movies: List<Movie>): Boolean {
        if (title.isBlank()) {
            printError(ErrorMessages.MovieErrors.BLANK_TITLE_ERROR)
            return false
        }
        if (movie.title == title) {
            printError(
                ErrorMessages.showErrorMessage(
                    "Your input couldn't be same to old title, ",
                    "Old Title: ${movie.title}"
                )
            )
            return false
        }

        val result = isTitleExist(title, movies)
        if (result) {
            printError(ErrorMessages.showErrorMessage("Same Movie Title Found, ", "title: ${movie.title}"))
            return false
        }
        return true
    }

    private fun isValidDirector(director: String, movie: Movie): Boolean {
        if (director.isBlank()) {
            printError(ErrorMessages.MovieErrors.BLANK_DIRECTOR_ERROR)
            return false
        }
        if (movie.director == director) {
            printError(
                ErrorMessages.showErrorMessage(
                    "Your input couldn't be same to old director",
                    "Old Director: ${movie.director}"
                )
            )
            return false
        }
        return true
    }

    private fun isValidRate(rate: Int, movie: Movie): Boolean {
        if (rate !in MainObject.MIN_ID_AND_RATE..MainObject.MAX_RATE) {
            printError(ErrorMessages.MovieErrors.RATING_RANGE_ERROR)
            return false
        }
        if (movie.rating == rate) {
            printError(
                ErrorMessages.showErrorMessage(
                    "Your input couldn't be same to old rating",
                    "Old Rating: ${movie.rating}"
                )
            )
            return false
        }
        return true
    }

    private fun getEditMenu(): Int? {
        val editChoose = MainObject.readIntSafely(
            """
            🔵What You Want To Edit ?
            
            1. Title ✉️
            2. Director 🎯
            3. Rate ⭐
            
            ~Choose >
        """.trimIndent()
        ) ?: return 0

        when (editChoose) {
            1 -> return 1
            2 -> return 2
            3 -> return 3
            else -> {
                printError(ErrorMessages.MenuErrors.MENU_1_TO_3_ERROR)
                return null
            }
        }
    }

}

object AuthUtilities {
    val password by lazy {
        MainObject.dataFile.readLines().getOrElse(1) { "" }
    }
    private var usernameKey: String? = null
    private var passwordKey: String? = null

    fun checkPasswordStrength(password: String): PasswordStrength? {
        if (password.isBlank()) return null

        val hasLowercase = password.any { it.isLowerCase() }
        val hasUppercase = password.any { it.isUpperCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecial = password.any { !it.isLetterOrDigit() }

        val types = listOf(
            hasLowercase,
            hasUppercase,
            hasDigit,
            hasSpecial
        ).count { it }

        return when {
            password.length < 8 -> PasswordStrength.LOW
            password.length >= 13 && types >= 3 -> PasswordStrength.HIGH
            password.length in 8..12 && types <= 2 -> PasswordStrength.MEDIUM
            else -> PasswordStrength.HIGH
        }
    }

    fun isValidRegister(user: User, passwordRepeat: String): Boolean {
        if (user.username.isBlank() || user.password.isBlank()) {
            printError(ErrorMessages.LoginErrors.BLANK_USERNAME_OR_PASSWORD_ERROR)
            return false
        }
        if (checkPasswordStrength(user.password) == PasswordStrength.LOW) {
            printError(ErrorMessages.LoginErrors.PASSWORD_LENGTH_ERROR)
            return false
        }
        if (user.password != passwordRepeat) {
            printError(ErrorMessages.LoginErrors.REG_PASSWORD_REPEAT_ERROR)
            return false
        }
        return true
    }

    fun isValidLogin(user: User): Boolean {
        val lines = MainObject.dataFile.readLines()

        if (lines.size < 4) {
            printError(ErrorMessages.LoginErrors.USER_DATA_NOT_FOUND_ERROR)
            return false
        }

        val encryptedUsername = lines[0]
        usernameKey = lines[1]
        val encryptedPassword = lines[2]
        passwordKey = lines[3]


        val storedUsername = MainObject.encryptionObject.decrypt(encryptedUsername, usernameKey!!)
        val storedPassword = MainObject.encryptionObject.decrypt(encryptedPassword, passwordKey!!)

        if (user.username != storedUsername || user.password != storedPassword) {
            printError(ErrorMessages.LoginErrors.INCORRECT_LOGIN_USERNAME_OR_PASSWORD_ERROR)
            return false
        }
        return true
    }

    fun isValidPassword(password: String): Boolean {
        if (password.isBlank()) {
            printError(ErrorMessages.LoginErrors.BLANK_PASSWORD_ERROR)
            return false
        }

        if (checkPasswordStrength(password) == PasswordStrength.LOW) {
            printError(ErrorMessages.LoginErrors.PASSWORD_LENGTH_ERROR)
            return false
        }

        if (password == this.password) {
            printError(ErrorMessages.LoginErrors.SAME_PASSWORD_ERROR)
            return false
        }

        return true
    }

}