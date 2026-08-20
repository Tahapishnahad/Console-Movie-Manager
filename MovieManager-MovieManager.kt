//Coded by Tahapishnahad
// tahapishnahad0@gmail.com


class MovieManager {
    private val movies = mutableListOf<Movie>()

    fun addMovie() {
        println("--== Add Movie ==--")
        print("Enter movie title: ")
        val addMovieNameInput = readln()
        print("Enter movie director: ")
        val addMovieDirectorInput = readln()
        val addMovieRateInput = MainObject.readIntSafely("Enter Rating From 1 to 10 : ") ?: return
        val addMovieIdInput = MainObject.readIntSafely("Enter Id From 1 to 100 : ") ?: return

        val resultObject =
            Movie(addMovieNameInput.trim(), addMovieDirectorInput.trim(), addMovieRateInput, addMovieIdInput)

        if (MovieExtension.validateMovie(resultObject, movies)) {
            println("Your movie has been added")
            movies.add(resultObject)
        }
    }

    fun removeMovie() {
        if (movies.isNotEmpty()) {
            println("Movies : ")
            movies.forEach {
                MovieExtension.printMovie(it)
                println("===========================")
            }
            val deleteMovieChoose = MainObject.readIntSafely(
                """
            --== Remove Movie ==--
            
            How you want to delete movie :
            
            1. ID
            2. Title
            
            ~Choose> 
        """.trimIndent()
            )

            when (deleteMovieChoose) {
                1 -> {
                    val removeMovieIdInput = MainObject.readIntSafely("Enter Movie Id: ") ?: return
                    val result = MovieExtension.findMovieById(removeMovieIdInput, movies)

                    if (result != null) {
                        println("Movie ${result.title} Removed")
                        movies.remove(result)

                    } else
                        println("Movie Not Found")

                }

                2 -> {
                    print("Enter Movie Title:  ")
                    val movieRemoveTitleInput = readln()
                    val result = MovieExtension.findMovieByTitle(movieRemoveTitleInput, movies)

                    if (result != null) {
                        println("Movie Removed")
                        movies.remove(result)
                    } else
                        println("Movie Not Found")

                }

                else -> println("Enter 1 or 2")
            }
        }else
            println("Nothing to remove")
    }

    fun editMovie() {
        if (movies.isNotEmpty()) {
            println("-== Edit Movie ==--")
            println("Movies : ")
            movies.forEach {
                MovieExtension.printMovie(it)
                println("==================")
            }
            val editMovieChoose = MainObject.readIntSafely(
                """
                How you want to edit movie : 
                
                1. ID
                2. Title
                
                ~Choose >
            """.trimIndent()
            )

            when (editMovieChoose) {
                1 -> {

                    val editIdInput = MainObject.readIntSafely("Enter Movie Id: ") ?: return

                    val result = MovieExtension.findMovieById(editIdInput, movies)
                    if (result != null) {
                        println("Movie Found")
                        MovieExtension.printMovie(result)
                        when (MovieExtension.getEditMenu()) {
                            1 -> {
                                println("-- Edit Title --")
                                print("Enter New Title: ")
                                val titleEditInput = readln()

                                if (MovieExtension.validateTitle(titleEditInput, result, movies)) {

                                    println("Movie title Updated")
                                    result.title = titleEditInput
                                }
                            }

                            2 -> {
                                println("-- Edit Director --")
                                print("Enter New Director: ")
                                val directorEditInput = readln()

                                if (MovieExtension.validateDirector(directorEditInput, result)) {
                                    println("Movie Director Updated")
                                    result.director = directorEditInput
                                }
                            }

                            3 -> {
                                println("-- Edit Rate --")

                                val rateEditInput = MainObject.readIntSafely("Enter New Rate :") ?: return
                                if (MovieExtension.validateRate(rateEditInput, result)) {
                                    println("Movie Rate Updated")
                                    result.rate = rateEditInput
                                }
                            }
                        }
                    } else
                        println("Movie Not Found")
                }

                2 -> {

                    print("Enter Movie Title: ")
                    val titleEditInput = readln()

                    val result = MovieExtension.findMovieByTitle(titleEditInput, movies)
                    if (result != null) {
                        println("Movie Found")
                        MovieExtension.printMovie(result)
                        when (MovieExtension.getEditMenu()) {
                            1 -> {
                                println("-- Edit Title --")
                                print("Enter New Title: ")
                                val titleEditInputByTitle = readln()

                                if (MovieExtension.validateTitle(titleEditInputByTitle, result, movies)) {
                                    println("Movie title Updated")
                                    result.title = titleEditInputByTitle
                                }
                            }

                            2 -> {
                                println("-- Edit Director --")
                                print("Enter New Director: ")
                                val directorEditInput = readln()

                                if (MovieExtension.validateDirector(directorEditInput, result)) {
                                    println("Movie Director Updated")
                                    result.director = directorEditInput
                                }
                            }

                            3 -> {
                                println("-- Edit Rate --")
                                val rateEditInput = MainObject.readIntSafely("Enter Rate :") ?: return

                                if (MovieExtension.validateRate(rateEditInput, result)) {
                                    println("Movie Rate Updated")
                                    result.rate = rateEditInput
                                }
                            }
                        }
                    } else
                        println("Movie Not Found")

                }

                else -> println("Enter 1 or 2")
            }

        } else
            println("Nothing to Edit")
    }


    fun showAllMovies() {
        if (movies.isNotEmpty()) {
            println("-== All Movies ==-")
            var count = 1
            movies.forEach {
                println("$count.")
                MovieExtension.printMovie(it)
                println("==========================")
                count++
            }

        } else
            println("Nothing To Show")
    }

    fun searchMovie() {
        if (movies.isNotEmpty()) {
            println("-== Search ==-")
            val searchChooseInput = MainObject.readIntSafely(
                """
                you want to search movie by:
                
                1. ID
                2. Title
                
                ~Choose >
            """.trimIndent()
            )

            when (searchChooseInput) {
                1 -> {
                    println("-== Search By ID -==")
                    val searchIdInput = MainObject.readIntSafely("Enter Movie Id: ") ?: return
                    val result = MovieExtension.findMovieById(searchIdInput, movies)

                    if (result != null) {
                        println("Movie Found")
                        MovieExtension.printMovie(result)

                    } else
                        println("Movie Not Found")

                }

                2 -> {
                    println("-== Search By Title -==")

                    print("Enter Movie Title: ")
                    val searchTitleInput = readln()
                    val result = MovieExtension.searchMovieByTitle(searchTitleInput, movies)

                    if (result.isNotEmpty()) {
                        println("Movie Found")
                        result.forEach {
                            MovieExtension.printMovie(it)
                        }
                    }
                }

                else -> println("Enter from 1 or 2")
            }
        } else
            println("Nothing to search")
    }

    fun sortMoviesByRate() {

        if (movies.isNotEmpty()) {
            val sortedList = movies.sortedByDescending { it.rate }

            sortedList.forEach {
                MovieExtension.printMovie(it)
                println("==================")
            }
        } else
            println("Nothing to sort")

    }

}

object MovieExtension {

    fun validateMovie(movie: Movie, movies: List<Movie>): Boolean {
        if (movie.title.isBlank() || movie.director.isBlank()) {
            println("You must enter a title or director")
            return false
        }

        if (movie.rate !in 1..10) {
            println("You must enter a valid rate")
            return false
        }

        if (movie.id !in 1..100) {
            println("You must enter a valid id")
            return false
        }

        if (movies.find { it.director == movie.director && it.title == movie.title } != null) {
            println("Same movie found")
            return false
        }

        if (movies.find { it.id == movie.id } != null) {
            println("Same movie id found")
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

        movie.run {
            println(
                """
                📝 Movie Title: $title 
                👥 Movie Director: $director
                ⭐ Movie Rate: $rate
                🆔 Movie ID: $id
            """.trimIndent()
            )
        }
    }

    fun validateTitle(title: String, movie: Movie, movies: List<Movie>): Boolean {

        if (title.isBlank()) {
            println("You must enter a title")
            return false
        }
        if (movie.title == title) {
            println("Enter a new title")
            return false
        }

        if (movies.find { it.title == title } != null) {
            println("This data found in another movie")
            return false
        }
        return true
    }

    fun validateDirector(director: String, movie: Movie): Boolean {

        if (director.isBlank()) {
            println("You must enter a director")
            return false
        }
        if (movie.director == director) {
            println("Enter a new director")
            return false
        }
        return true
    }

    fun validateRate(rate: Int, movie: Movie): Boolean {

        if (rate !in 1..10) {
            println("You must enter a in range rate")
            return false
        }
        if (movie.rate == rate) {
            println("You must enter a new rate")
            return false
        }
        return true
    }

    fun getEditMenu(): Int? {
        val editChoose = MainObject.readIntSafely(
            """
            What You Want To Edit ?
            
            1. Title
            2. Director
            3. Rate
            
            ~Choose >
        """.trimIndent()
        )

        when (editChoose) {
            1 -> return 1
            2 -> return 2
            3 -> return 3
            else -> println("Enter from 1 to 3")
        }
        return null
    }

}


data class Movie(
    var title: String,
    var director: String,
    var rate: Int,
    val id: Int

)
