# Console-Movie-Manager
# Movie Manager - A Kotlin Console Application

![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=flat&logo=kotlin)
![License](https://img.shields.io/badge/License-MIT-green)

A feature-rich console-based movie management system built with Kotlin, featuring user authentication, CRUD operations, and interactive menu-driven interface.

## 🎬 Features

### 🔐 User Management
- **Registration System** - First-time user registration with password strength validation
- **Secure Login** - Credential verification with persistent storage
- **Password Management** - Change password functionality with strength checking
- **Logout System** - Secure session termination

### 🎥 Movie Operations
- **Add Movies** - Create new movie entries with title, director, rating, and ID
- **Remove Movies** - Delete movies by ID or title
- **Edit Movies** - Update title, director, or rating (search by ID or title)
- **View All Movies** - Display complete movie collection with details
- **Search Movies** - Find movies by ID (exact match) or title (partial match)
- **Sort Movies** - View movies sorted by rating (highest first)

### ✨ User Experience
- **Intuitive Menu System** - Clear navigation with emoji indicators
- **Input Validation** - Robust error handling for all user inputs
- **Data Persistence** - User credentials stored in local file system
- **Safe Type Conversion** - Secure integer parsing with user feedback

## 🚀 Getting Started

### Prerequisites
- Kotlin 1.9 or higher
- Java Runtime Environment (JRE)
- Any IDE with Kotlin support (IntelliJ IDEA recommended)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Tahapishnahad/movie-manager.git
   cd movie-manager
   ```

2. **Compile the project**
   ```bash
   kotlinc *.kt -include-runtime -d MovieManager.jar
   ```

3. **Run the application**
   ```bash
   java -jar MovieManager.jar
   ```

### First Time Setup
1. The application will detect if it's your first run
2. Create a username and secure password
3. Password strength requirements:
   - Minimum 8 characters
   - Mix of uppercase, lowercase, digits, and special characters recommended
4. You'll be automatically logged in

## 📝 Usage Guide

### Main Menu Options
```
1. Add Movie 🎞️     - Create new movie entry
2. Remove Movie 📛   - Delete existing movie
3. Edit Movie 📝    - Modify movie details
4. Show All Movies 🌎 - View entire collection
5. Search Movie 🔍   - Find specific movies
6. Sort By Rate ⭐  - View movies by rating
7. Change Password 🔑 - Update user password
8. Logout 🚶🏻‍♂️‍➡️   - End current session
9. Exit 👋🏻         - Close application
```

### Movie Data Structure
```
📝 Title: Movie Name
👥 Director: Director Name
⭐ Rating: 1-10
🆔 ID: 1-100
```

## 🛠️ Technical Architecture

### Core Components

| File | Responsibility |
|------|---------------|
| `MovieManager-Main.kt` | Application entry point and main loop |
| `MovieManager-MainObject.kt` | Singleton object with shared utilities and menu system |
| `MovieManager-MovieManager.kt` | Movie CRUD operations and business logic |
| `MovieManager-RegisterAndLogin.kt` | User authentication and password management |

### Key Classes & Objects

- **`Movie`** (Data Class) - Movie entity with title, director, rating, and ID
- **`User`** (Data Class) - User credentials model
- **`MovieManager`** - Business logic for movie operations
- **`RegisterAndLogin`** - User authentication handling
- **`MovieExtension`** - Static utility methods for movie operations
- **`LoginAndRegisterValidation`** - Validation logic for authentication

### Security Features
- Password strength validation (LOW/MEDIUM/HIGH)
- Credential persistence with file-based storage
- Session management with logout functionality
- Input sanitization and validation

## 🔄 Data Flow

```
┌─────────────┐
│   Main.kt   │ (Entry Point)
└──────┬──────┘
       │
┌──────▼──────┐
│  MainObject │ (Singleton)
└──────┬──────┘
       │
       ├───────────────┐
       │               │
┌──────▼──────┐ ┌──────▼──────┐
│ MovieManager│ │RegisterLogin│
└──────┬──────┘ └──────┬──────┘
       │               │
┌──────▼──────┐ ┌──────▼──────┐
│  Extension  │ │ Validation  │
└─────────────┘ └─────────────┘
```

## 🧪 Error Handling

The application includes comprehensive error handling:
- **Invalid Input**: User-friendly prompts for correct input
- **Duplicate Entries**: Prevention of duplicate movie titles/IDs
- **Empty Operations**: Graceful handling of empty collections
- **Authentication**: Clear feedback for failed login attempts

## 💻 Code Examples

### Adding a Movie
```kotlin
fun addMovie() {
    println("--== Add Movie ==--")
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

    if (MovieExtension.validateMovie(resultObject, movies)) {
        println("Your movie has been added")
        movies.add(resultObject)
    }
}
```

### Password Validation
```kotlin
fun checkPasswordStrength(password: String): PasswordStrength? {
    if (password.isBlank()) return null
    
    val hasLowercase = password.any { it.isLowerCase() }
    val hasUppercase = password.any { it.isUpperCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecial = password.any { !it.isLetterOrDigit() }
    
    val types = listOf(hasLowercase, hasUppercase, hasDigit, hasSpecial).count { it }
    
    return when {
        password.length < 8 -> PasswordStrength.LOW
        password.length >= 13 && types >= 3 -> PasswordStrength.HIGH
        types >= 2 -> PasswordStrength.MEDIUM
        else -> PasswordStrength.LOW
    }
}
```

## 📁 Project Structure

```
movie-manager/
├── MovieManager-Main.kt              # Application entry point
├── MovieManager-MainObject.kt        # Singleton and utilities
├── MovieManager-MovieManager.kt      # Movie operations
├── MovieManager-RegisterAndLogin.kt  # Authentication
├── userdata.txt                      # User credentials storage
└── README.md                         # Documentation
```

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Areas for Improvement
- [ ] Add database storage instead of file system
- [ ] Implement movie rating system
- [ ] Add categories/genres
- [ ] Create GUI interface
- [ ] Add export/import functionality
- [ ] Implement search by director
- [ ] Add release year tracking

## 📄 License

This project is open source and available under the MIT License.

## 📞 Contact

**Tahapishnahad**  
📧 tahapishnahad0@gmail.com

---

⭐ If you find this project useful, please consider giving it a star!
