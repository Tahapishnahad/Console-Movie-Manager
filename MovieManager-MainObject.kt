//Coded by Tahapishnahad
// tahapishnahad0@gmail.com


import java.io.File

object MainObject {
    val registerAndLoginObject = RegisterAndLogin()
    val movieObject = MovieManager()
    val dataFile = File ("./userdata.txt")

    fun readIntSafely(arg: String): Int? {
        print(arg)
        var input = readln().toIntOrNull()

        while (input == null) {

            println("Your Input is Invalid")
            println("Enter a Number: ")
            val againInput = readln()
            input = againInput.toIntOrNull()

        }

        return input
    }

    fun printMenu() {
        print(
            """
            -=-=- Main Menu -=-=-
            
            1. Add Movie 🎞️
            2. Remove Movie 📛
            3. Edit Movie 📝
            4. Show All Movies 🌎
            5. Search Movie 🔍
            6. Sort Movies By Rate ⭐
            7. Change Password 🔑
            8. Logout 🚶🏻‍♂️‍➡️
            9. Exit 👋🏻
            
            ~Choose > 
        """.trimIndent()
        )
    }

    fun getMenuData(): Int {
        val menuInput = readIntSafely("") ?: return -1

        return menuInput
    }
}