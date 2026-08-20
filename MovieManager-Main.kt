//Coded by Tahapishnahad
// tahapishnahad0@gmail.com

fun main() {

    while (true){
        if (!MainObject.dataFile.exists()){
            MainObject.dataFile.createNewFile()

        }
        if (MainObject.dataFile.readLines().isNotEmpty()) {
            val result = MainObject.registerAndLoginObject.getLoginData()
            if (LoginAndRegisterValidation.validateLogin(result)) {
                println("True data , please wait")
                break
            }

        } else {
            val result = MainObject.registerAndLoginObject.getRegisterData()
            if (LoginAndRegisterValidation.validateRegister(result.first, result.second)) {
                MainObject.dataFile.writeText("${result.first.username}\n${result.first.password}")
                println("User registered , please wait")
                break

            }

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
                MainObject.movieObject.sortMoviesByRate()

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


            else -> println("Enter from 1 to 9")

        }

    }


}