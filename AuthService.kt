//Coded by Tahapishnahad
// tahapishnahad0@gmail.com

class AuthService {
    fun getRegisterData(): Pair<User, String> {
        println("🔵 Welcome to App for first time")
        println("--(  Register  )--")
        print("Username: ")
        val username = readln()
        print("Password: ")
        val password = readln()
        print("Repeat Password: ")
        val passwordRepeat = readln()

        return Pair(User(username, password), passwordRepeat)

    }

    fun getLoginData(): User {
        println("🟢 Thanks To Use Our App ! ")
        println("--(  Login  )--")
        print("Username: ")
        val username = readln()
        print("Password: ")
        val password = readln()

        return User(username, password)
    }

    fun changePassword() {
        println("🔑 Change Password 🔑")


        val lines = MainObject.dataFile.readLines()
        if (lines.size < 4) {
            printError("Password data not found!")
            return
        }

        val encryptedPassword = lines[2]
        val passwordKey = lines[3]


        val currentPassword = MainObject.encryptionObject.decrypt(encryptedPassword, passwordKey)

        println(
            "Your Password Strength: ${
                AuthUtilities.checkPasswordStrength(currentPassword)?.name
            }"
        )

        print("Enter New Password: ")
        val newPasswordInput = readln()

        if (AuthUtilities.isValidPassword(newPasswordInput)) {

            val newEncryptedPassword = MainObject.encryptionObject.encrypt(newPasswordInput)


            val newData = """
            ${lines[0]}
            ${lines[1]}
            ${newEncryptedPassword.first}
            ${newEncryptedPassword.second}
        """.trimIndent()

            MainObject.dataFile.writeText(newData)
            printSuccess(SuccessMessages.PASSWORD_CHANGED)
        }
    }

    fun logOut() {
        printSuccess(SuccessMessages.LOGGING_OUT)
        MainObject.dataFile.writeText("")
    }


}

data class User(
    val username: String,
    val password: String

)

enum class PasswordStrength {
    LOW,
    MEDIUM,
    HIGH

}