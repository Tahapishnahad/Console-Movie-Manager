# 🎬 Movie Manager v1.1 - Kotlin Console Application

![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=flat&logo=kotlin)
![Version](https://img.shields.io/badge/version-1.1-brightgreen)
![License](https://img.shields.io/badge/License-MIT-green)

A feature-rich console-based movie management system built with Kotlin, featuring user authentication, CRUD operations, and an interactive menu-driven interface.

## 🆕 What's New in Version 1.1

Version 1.1 brings a complete architectural overhaul with improved separation of concerns, better security, and enhanced code maintainability:

### 🏗️ **New Modular Architecture**
- **`AuthService.kt`** - Dedicated authentication and user management logic
- **`MovieService.kt`** - Centralized movie CRUD operations and business logic
- **`ConsoleManager.kt`** - Unified console I/O and menu handling
- **`Encryption.kt`** - Basic password protection and data security
- **`Utility.kt`** - Shared utility functions and extensions

### 🔐 **Enhanced Security**
- Basic password encryption for stored credentials
- Improved password strength validation
- Better session management with clear logout

### 📁 **Better Code Organization**
- Clear separation of concerns between services
- More maintainable and testable codebase
- Simplified main application loop

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
   git clone https://github.com/Tahapishnahad/Console-Movie-Manager.git
   cd Console-Movie-Manager
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
10. About         - Show project info
```

### Movie Data Structure
```
📝 Title: Movie Name
👥 Director: Director Name
⭐ Rating: 1-10
🆔 ID: 1-100
```

## 🛠️ Technical Architecture

### Core Components (v1.1)

| File | Responsibility |
|------|---------------|
| `Main.kt` | Application entry point and main loop |
| `MainObject.kt` | Singleton object with shared utilities |
| `AuthService.kt` | **NEW** Authentication and user management |
| `MovieService.kt` | **NEW** Movie CRUD operations |
| `ConsoleManager.kt` | **NEW** Console I/O and menu handling |
| `Encryption.kt` | **NEW** Password encryption and security |
| `Utility.kt` | **NEW** Shared utility functions |

### Key Classes & Objects

- **`Movie`** (Data Class) - Movie entity with title, director, rating, and ID
- **`User`** (Data Class) - User credentials model
- **`AuthService`** - Handles registration, login, and password changes
- **`MovieService`** - Business logic for all movie operations
- **`ConsoleManager`** - Manages all console input/output
- **`Encryption`** - Provides basic password protection

### Security Features
- Password strength validation (LOW/MEDIUM/HIGH)
- Basic password encryption for storage
- Credential persistence with file-based storage
- Session management with logout functionality
- Input sanitization and validation

## 🔄 Data Flow (v1.1)

```
┌─────────────┐
│   Main.kt   │ (Entry Point)
└──────┬──────┘
       │
┌──────▼──────┐
│  MainObject │ (Singleton)
└──────┬──────┘
       │
       ├───────────────┬───────────────┐
       │               │               │
┌──────▼──────┐ ┌──────▼──────┐ ┌──────▼──────┐
│ AuthService │ │MovieService │ │ConsoleManager│
└──────┬──────┘ └──────┬──────┘ └──────┬──────┘
       │               │               │
┌──────▼──────┐ ┌──────▼──────┐ ┌──────▼──────┐
│ Encryption  │ │  Utility    │ │   Input/    │
│             │ │             │ │   Output    │
└─────────────┘ └─────────────┘ └─────────────┘
```

## 🧪 Error Handling

The application includes comprehensive error handling:
- **Invalid Input**: User-friendly prompts for correct input
- **Duplicate Entries**: Prevention of duplicate movie titles/IDs
- **Empty Operations**: Graceful handling of empty collections
- **Authentication**: Clear feedback for failed login attempts

## 💻 Code Examples

### Adding a Movie (v1.1)
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

### Password Encryption (v1.1)
```kotlin
// Encryption.kt
class Encryption {
    fun encrypt(password: String): String {
        // Basic encryption for password storage
        return password.reversed() // Simple example
    }
    
    fun decrypt(encrypted: String): String {
        return encrypted.reversed()
    }
}
```

## 📁 Project Structure (v1.1)

```
Console-Movie-Manager/
├── Main.kt                    # Application entry point
├── MainObject.kt              # Singleton and utilities
├── AuthService.kt             # **NEW** Authentication logic
├── MovieService.kt            # **NEW** Movie operations
├── ConsoleManager.kt          # **NEW** Console management
├── Encryption.kt              # **NEW** Security utilities
├── Utility.kt                 # **NEW** Shared functions
├── userdata.txt               # User credentials storage
├── LICENSE                    # MIT License
└── README.md                  # Documentation
```

## 🔄 Migration from v1.0 to v1.1

If you were using version 1.0, here's what changed:
1. **File Structure** - Files are now organized by responsibility
2. **Services** - Logic moved to dedicated service classes
3. **Encryption** - Passwords are now encrypted before storage
4. **Input/Output** - Console operations centralized in ConsoleManager
5. **Main Loop** - Simplified and more readable

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Future Plans
- [ ] Add database storage instead of file system
- [ ] Add categories/genres for movies
- [ ] Implement search by director
- [ ] Add release year tracking
- [ ] Adding new features 

## 📄 License

This project is open source and available under the MIT License.

## 📞 Contact

**Tahapishnahad**  
📧 tahapishnahad0@gmail.com

---

⭐ If you find this project useful, please consider giving it a star!

---

**Wrote with help of DeepseekAI** © 2026 Taha Pishnahad. All rights reserved.
