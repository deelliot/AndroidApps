# Recipe Explorer App

## Introduction

Welcome to the Recipe Explorer App! This simple Android application was developed as a learning project to explore the usage of Jetpack Compose and StateFlows within the MVVM (Model-View-ViewModel) architecture. The app utilizes a JSON dummy API to retrieve a list of recipes, showcasing the integration of key technologies such as Kotlin Coroutines, Retrofit, Moshi, and Coil.

## Overview

The Recipe Explorer App features a clean and straightforward user interface with a single screen displaying a list of recipes. Built using Kotlin, the app follows the MVVM architecture, providing a structured and maintainable codebase. Jetpack Compose and StateFlows are employed to create a reactive UI that updates seamlessly in response to changes in the underlying data.

## Features

- **Recipe Listing**: Users can view a list of recipes on the main screen.

- **MVVM Architecture**: The app adheres to the MVVM architecture pattern, ensuring a clear separation of concerns.

- **Jetpack Compose**: The modern Android UI toolkit, Jetpack Compose, is utilized to create a declarative and reactive user interface.

- **StateFlows**: StateFlows are employed to manage and propagate the state of the UI, enabling efficient updates.

- **Kotlin Coroutines**: Asynchronous tasks, such as network requests, are handled using Kotlin Coroutines, ensuring a responsive user experience.

- **Retrofit**: The app utilizes Retrofit to retrieve data from the JSON dummy API. Retrofit simplifies the process of making network requests and handling responses.

- **Moshi**: Moshi is employed for parsing JSON responses from the dummy API, facilitating the extraction of relevant information.

- **Coil**: Coil is used for loading and displaying images efficiently, converting image URLs into actual images.

## Getting Started

To build and run the app locally, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/deelliot/AndroidApps/tree/main/Recipes
   ```

2. Open the project in Android Studio.

3. Build and run the app on an emulator or a physical device.

## Dependencies

The app relies on the following key dependencies, which are managed through Gradle:

- [Jetpack Compose](https://developer.android.com/jetpack/compose): For building the UI in a declarative and reactive manner.
- [StateFlows](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow): For managing and propagating UI state.
- [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines): For handling asynchronous tasks.
- [Retrofit](https://github.com/square/retrofit): For making network requests.
- [Moshi](https://github.com/square/moshi): For parsing JSON responses from the dummy API.
- [Coil](https://github.com/coil-kt/coil): For loading and displaying images efficiently.

Check the `build.gradle` files for the latest versions of these dependencies.

Happy recipe exploring! 🍽️
