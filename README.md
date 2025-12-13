# CPVault

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](...)

CPVault is a Kotlin Multiplatform application designed to help competitive programmers track their progress, manage problems, and stay updated with upcoming contests.

## ✨ Features

*   Track your contest ratings from various platforms.
*   Manage a list of practice problems.
*   View upcoming contest schedules.
*   ... (add more features)

## 📸 Screenshots

*(Add screenshots or GIFs of the application here to showcase its features and UI.)*

## 🛠️ Technologies Used

*   [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html) for shared business logic.
*   [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/) for the shared UI.
*   ... (add other libraries like Ktor, SQLDelight, etc. if they are used)

## 📂 Project Structure

This is a Kotlin Multiplatform project targeting Android, iOS, Web, and Desktop (JVM).

*   `./composeApp/src`: This directory contains the shared code for all platforms.
    *   `commonMain`: Code common to all targets.
    *   `androidMain`: Android-specific code.
    *   `iosMain`: iOS-specific code.
    *   `jvmMain`: Desktop (JVM)-specific code.
    *   `wasmJsMain`: Web (Wasm) specific code.
*   `./iosApp`: This directory contains the iOS application entry point and any SwiftUI code.

## 🚀 Getting Started

### Prerequisites

*   ... (mention any prerequisites like JDK, Android Studio, Xcode, etc.)

### Building and Running

You can build and run the application for different platforms using either your IDE's run configurations or the command line.

#### Android

```shell
# On macOS/Linux
./gradlew :composeApp:assembleDebug

# On Windows
.\gradlew.bat :composeApp:assembleDebug
```

#### Desktop (JVM)

```shell
# On macOS/Linux
./gradlew :composeApp:run

# On Windows
.\gradlew.bat :composeApp:run
```

#### Web

*   **Wasm target (faster, modern browsers):**
    ```shell
    # On macOS/Linux
    ./gradlew :composeApp:wasmJsBrowserDevelopmentRun

    # On Windows
    .\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun
    ```
*   **JS target (slower, supports older browsers):**
    ```shell
    # On macOS/Linux
    ./gradlew :composeApp:jsBrowserDevelopmentRun

    # On Windows
    .\gradlew.bat :composeApp:jsBrowserDevelopmentRun
    ```

#### iOS

Open the `/iosApp` directory in Xcode and run the project from there.

## 🤝 Contributing

Contributions are welcome! If you'd like to contribute, please fork the repository and create a pull request. For major changes, please open an issue first to discuss what you would like to change.

## 💬 Feedback

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP).
