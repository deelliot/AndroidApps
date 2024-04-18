# What's Cooking

## Introduction
Welcome to What's Cooking! This Android app is your recipe browsing companion, designed to showcase an array of recipes sourced from a dummy API. Developed as a learning project, What's Cooking focuses on utilizing Jetpack Compose, StateFlows, Room, and Hilt within the MVVM architecture.

## Overview

What's Cooking offers users a chance to explore recipes, with filtering options based on meal type, sorting functionality by total time or top-rated recipes, searching through recipes with keywords, and now, the ability to add your own recipes. This app is still a work in progress and primarily created as a learning tool. Built using Kotlin, the app leverages Kotlin Coroutines, Retrofit, Moshi, Coil, Room, and Hilt to handle asynchronous tasks, retrieve data, parse JSON responses, load images efficiently, and manage local database storage and dependency injection.

## Features

- **Recipe Browsing**: Explore a diverse range of recipes, ready to tantalize your taste buds.

- **Filtering Options**: Filter recipes by meal type, allowing for easy navigation based on preferences.

- **Sorting Functionality**: Sort recipes by total time or top-rated, enabling quick access to desired recipes.

- **Search**: A search feature enables users to find recipes quickly by name, enhancing the app's usability even further.

- **Add Your Own Recipes**: Introducing a new feature that allows users to add their own recipes to the app, making it a more interactive experience.

- **MVVM Architecture**: What's Cooking adheres to the MVVM architecture pattern, promoting modularity and maintainability.

- **Jetpack Compose**: The modern Android UI toolkit, Jetpack Compose, is utilized to craft a visually stunning and interactive user interface.

- **StateFlows**: StateFlows are employed to manage the state of the UI, ensuring smooth and responsive updates.

- **Kotlin Coroutines**: Asynchronous tasks, such as network requests and database operations, are efficiently handled using Kotlin Coroutines, enhancing the app's performance.

- **Retrofit**: The app utilizes Retrofit to retrieve data from the dummy API, streamlining the process of making network requests and handling responses.

- **Moshi**: Moshi is employed for parsing JSON responses from the dummy API, facilitating seamless extraction of relevant information.

- **Coil**: Coil is used for loading and displaying images efficiently, enhancing the visual appeal of recipe listings.

- **Room**: Room is used for local database storage, allowing users to add and manage their own recipes.

- **Hilt**: Hilt is used for dependency injection, improving code maintainability and testability.

## Getting Started

To experience the culinary wonders of What's Cooking, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/deelliot/AndroidApps/tree/9fd898b29cbc66b5e58345d4c64811c57ff8bb1b/WhatsCooking
   ```

2. Open the project in Android Studio.

3. Build and run the app on an emulator or a physical device.

## Dependencies

The app relies on the following key dependencies, managed through Gradle:

- [Jetpack Compose](https://developer.android.com/jetpack/compose): For building the UI in a declarative and reactive manner.
- [StateFlows](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow): For managing and propagating UI state.
- [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines): For handling asynchronous tasks.
- [Retrofit](https://github.com/square/retrofit): For making network requests.
- [Moshi](https://github.com/square/moshi): For parsing JSON responses from the dummy API.
- [Coil](https://github.com/coil-kt/coil): For loading and displaying images efficiently.
- [Room](https://developer.android.com/topic/libraries/architecture/room): For local database storage.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android): For dependency injection.

Refer to the `build.gradle` files for the latest versions of these dependencies.

Bon appétit and happy cooking with What's Cooking! 🍳👩‍🍳👨‍🍳

![HomeScreen](https://github.com/deelliot/AndroidApps/assets/93197340/3d406b97-7911-46b4-b419-047cfef7f9ea)
![RecipeDetails](https://github.com/deelliot/AndroidApps/assets/93197340/95e494bf-20ea-4706-81fc-2fdab44b1bd0)
![SearchScreen](https://github.com/deelliot/AndroidApps/assets/93197340/fdf9f13e-19be-41e3-acd4-895e8cc44b42)
