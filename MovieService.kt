// Coded by Tahapishnahad
// tahapishnahad0@gmail.com

class MovieManager {
    //Movies
    private val movies = mutableListOf<Movie>()

    //1. Add Movie
    fun addMovie() {
        println("➕ Add Movie ➕")
        print("Enter movie title: ")
        val addMovieNameInput = readln()
        print("Enter movie director: ")
        val addMovieDirectorInput = readln()
        val addMovieRateInput = MainObject.readIntSafely("Enter Rating From 1 to 10 : ") ?: return
        val addMovieIdInput = MainObject.readIntSafely("Enter Id From 1 to 100 : ") ?: return

        val resultObject = Movie(
            addMovieNameInput.trim(),
            addMovieDirectorInput.trim(),
            addMovieRateInput,
            addMovieIdInput
        )

        if (MovieUtilities.isValidMovie(resultObject, movies)) {
            printSuccess(SuccessMessages.showSuccessMessage("${addMovieNameInput.trim()} Added"))
            movies.add(resultObject)
        }
    }

    //2. Remove Movie
    fun removeMovie() {
        if (movies.isNotEmpty()) {
            println("❌ Remove Movie ❌")
            movies.forEach {
                MovieUtilities.printMovie(it)
            }
            val deleteMovieChoose = MainObject.readIntSafely(
                """

            🔵 How you want to delete movie :
            
            1. ID 0️⃣
            2. Title ✉️
            
            ~Choose > 
        """.trimIndent()
            ) ?: return

            when (deleteMovieChoose) {
                1 -> {
                    val removeMovieIdInput = MainObject.readIntSafely("Enter Movie Id: ") ?: return
                    val result = MovieUtilities.findMovieById(removeMovieIdInput, movies)

                    if (result != null) {
                        printSuccess(SuccessMessages.showSuccessMessage("${result.title} Removed"))
                        movies.remove(result)
                    } else {
                        printError(ErrorMessages.NothingErrors.MOVIE_NOT_FOUND_ERROR)
                    }
                }

                2 -> {
                    print("Enter Movie Title:  ")
                    val movieRemoveTitleInput = readln()
                    val result = MovieUtilities.findMovieByTitle(movieRemoveTitleInput, movies)

                    if (result != null) {
                        printSuccess(SuccessMessages.showSuccessMessage("${result.title} Removed"))
                        movies.remove(result)
                    } else {
                        printError(ErrorMessages.NothingErrors.MOVIE_NOT_FOUND_ERROR)
                    }
                }

                else -> printError(ErrorMessages.MenuErrors.MENU_1_OR_2_ERROR)
            }
        } else {
            printError(ErrorMessages.NothingErrors.NOTHING_TO_REMOVE_ERROR)
        }
    }

    //3. Edit Movie
    fun editMovie() {
        if (movies.isNotEmpty()) {
            println("📝 Edit Movie 📝")

            movies.forEach {
                MovieUtilities.printMovie(it)
            }
            val editMovieChoose = MainObject.readIntSafely(
                """
                🔵 How you want to edit movie : 
                
                1. ID 0️⃣
                2. Title ✉️
                
                ~Choose >
            """.trimIndent()
            ) ?: return

            when (editMovieChoose) {
                1 -> {
                    val editIdInput = MainObject.readIntSafely("Enter Movie Id: ") ?: return
                    val result = MovieUtilities.findMovieById(editIdInput, movies)

                    if (result != null) {
                        printSuccess(SuccessMessages.showSuccessMessage("Movie Found"))
                        MovieUtilities.printMovie(result)
                        MovieUtilities.editSwitcher(result, movies)

                    } else {
                        printError(ErrorMessages.NothingErrors.MOVIE_NOT_FOUND_ERROR)
                    }
                }

                2 -> {
                    print("Enter Movie Title: ")
                    val titleEditInput = readln()
                    val result = MovieUtilities.findMovieByTitle(titleEditInput, movies)

                    if (result != null) {
                        printSuccess(SuccessMessages.showSuccessMessage("Movie Found"))
                        MovieUtilities.printMovie(result)
                        MovieUtilities.editSwitcher(result, movies)

                    } else {
                        printError(ErrorMessages.NothingErrors.MOVIE_NOT_FOUND_ERROR)
                    }
                }

                else -> printError(ErrorMessages.MenuErrors.MENU_1_TO_3_ERROR)
            }

        } else {
            printError(ErrorMessages.NothingErrors.NOTHING_TO_EDIT_ERROR)
        }
    }

    //4.Show All Movies
    fun showAllMovies() {
        if (movies.isNotEmpty()) {
            println("🌍 All Movies 🌍")
            var count = 1
            movies.forEach {

                println("$count.")
                MovieUtilities.printMovie(it)
                count++
            }
        } else {
            printError(ErrorMessages.NothingErrors.NOTHING_TO_SHOW_ERROR)
        }
    }

    //5. Search Movies
    fun searchMovie() {
        if (movies.isNotEmpty()) {
            println("🔎 Search 🔎")
            val searchChooseInput = MainObject.readIntSafely(
                """
                🔵 You want to search movie by:
                
                1. ID 0️⃣
                2. Title ✉️
                
                ~Choose >
            """.trimIndent()
            ) ?: return

            when (searchChooseInput) {
                1 -> {
                    println("-- Search By ID --")
                    val searchIdInput = MainObject.readIntSafely("Enter Movie Id: ") ?: return
                    val result = MovieUtilities.findMovieById(searchIdInput, movies)

                    if (result != null) {
                        printSuccess(SuccessMessages.MOVIE_FOUND)
                        MovieUtilities.printMovie(result)
                    } else {
                        printError(ErrorMessages.NothingErrors.MOVIE_NOT_FOUND_ERROR)
                    }
                }

                2 -> {
                    println("-- Search By Title --")
                    print("Enter Movie Title: ")
                    val searchTitleInput = readln()
                    val result = MovieUtilities.searchMovieByTitle(searchTitleInput, movies)

                    if (result.isNotEmpty()) {
                        printSuccess(SuccessMessages.MOVIE_FOUND)
                        result.forEach {
                            MovieUtilities.printMovie(it)
                        }
                    } else {
                        printError(ErrorMessages.NothingErrors.MOVIE_NOT_FOUND_ERROR)
                    }
                }

                else -> printError(ErrorMessages.MenuErrors.MENU_1_OR_2_ERROR)
            }
        } else {
            printError(ErrorMessages.NothingErrors.NOTHING_TO_SEARCH_ERROR)
        }
    }

    //6. Sort Movies By Rating
    fun sortMoviesByRating() {
        if (movies.isNotEmpty()) {
            val sortedList = movies.sortedByDescending { it.rating }
            sortedList.forEach {
                MovieUtilities.printMovie(it)
            }
        } else {
            printError(ErrorMessages.NothingErrors.NOTHING_TO_SORT_ERROR)
        }
    }

}


data class Movie(
    var title: String,
    var director: String,
    var rating: Int,
    val id: Int

)
