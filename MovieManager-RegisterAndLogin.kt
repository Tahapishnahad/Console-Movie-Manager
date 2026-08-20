//Coded by Tahapishnahad
// tahapishnahad0@gmail.com


class RegisterAndLogin {
    fun getRegisterData(): Pair<User, String> {
        println("Welcome to App for first time")
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
        println("Thanks To Use Us App ! ")
        println("--(  Login  )--")
        print("Username: ")
        val username = readln()
        print("Password: ")
        val password = readln()

        return User(username, password)
    }

    fun changePassword() {
        println("-== Change Password ==-")
        println(
            "Your Password Strength: ${
                LoginAndRegisterValidation.checkPasswordStrength(password).toString().lowercase()
            }"
        )

        print("Enter New Password: ")
        val newPasswordInput = readln()

        if (LoginAndRegisterValidation.validatePassword(newPasswordInput)) {
            println("Password Updated !")
            val changeData =
                MainObject.dataFile.readText().replace(LoginAndRegisterValidation.password, newPasswordInput)
            MainObject.dataFile.writeText(changeData)
        }
    }

    fun logOut() {
        println("-== Logging Out ==-")
        MainObject.dataFile.writeText("")
    }


}

object LoginAndRegisterValidation {
    val password by lazy {   MainObject.dataFile.readText().split("\n")[1] }
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

            password.length >= 13 && types >= 3 ->
                PasswordStrength.HIGH

            types >= 2 ->
                PasswordStrength.MEDIUM

            else ->
                PasswordStrength.LOW
        }
    }

    fun validateRegister(user: User, passwordRepeat: String): Boolean {
        if (user.username.isBlank() || user.password.isBlank()) {
            println("username and password must not be empty")
            return false
        }
        if (checkPasswordStrength(user.password) == PasswordStrength.LOW) {
            println("Your password must have a good strength")
            return false
        }
        if (user.password != passwordRepeat) {
            println("Your password repeat must match the password")
            return false
        }
        return true
    }

    fun validateLogin(user: User): Boolean {

        val dataMined = MainObject.dataFile.readText().split("\n")

        if (user.username != dataMined[0] || user.password != dataMined[1]) {
            println("Incorrect username or password")
            return false
        }
        return true
    }

    fun validatePassword(password: String): Boolean {
        if (password.isBlank()) {
            println("Your input must not be empty")
            return false
        }

        if (checkPasswordStrength(password) == PasswordStrength.LOW) {
            println("Your password must have a good strength")
            return false
        }

        if (password == this.password) {
            println("you must enter a new password")
            return false
        }

        return true
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