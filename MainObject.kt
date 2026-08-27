//Coded by Tahapishnahad
// tahapishnahad0@gmail.com

import java.io.File

object MainObject {
    val registerAndLoginObject = AuthService()
    val movieObject = MovieManager()
    val encryptionObject  = Encryption()
    val dataFile = File ("./userdata.txt")
    const val MAX_ID = 100
    const val MAX_RATE = 10
    const val MIN_ID_AND_RATE = 1

    fun readIntSafely(arg: String, maxAttempts: Int = 3): Int? {
        print(arg)
        var attempts = 0
        while (attempts < maxAttempts) {
            val input = readlnOrNull()?.toIntOrNull()
            if (input != null) return input
            attempts++
            if (attempts < maxAttempts) {
                printError(ErrorMessages.MenuErrors.NON_NUMBER_INPUT_ERROR)
                print(arg)
            }
        }
        printError(ErrorMessages.MenuErrors.TOO_MANY_ATTEMPTS_ERROR)
        return null
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
            6. Sort Movies By Rating ⭐
            7. Change Password 🔑
            8. Logout 🚶🏻‍♂️‍➡️
            9. Exit 👋🏻
            10. About ℹ️
            
            ~Choose > 
        """.trimIndent()
        )
    }

    fun getMenuData(): Int {
        val menuInput = readIntSafely("") ?: return -1

        return menuInput
    }

    fun showAbout() {
        println("""
            
            ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                             Console Movie Manager
            ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

              About
              ──────────────────────────────────────────────────
              A powerful Kotlin console application.

              Project
              ──────────────────────────────────────────────────
              Version      1.1.1
              Developer    Taha
              Language     Kotlin
              Status       Stable

              Features
              ──────────────────────────────────────────────────
              ✓ Fast
              ✓ Lightweight
              ✓ Easy to use

              Links
              ──────────────────────────────────────────────────
              🌐 Website   https://tahapishnahad.github.io/Info/
              💻 GitHub    https://github.com/Tahapishnahad
              ⌨️ Repository https://github.com/Tahapishnahad/Console-Movie-Manager

            ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                             Thanks for using!
            ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            
        """.trimIndent())
    }
}