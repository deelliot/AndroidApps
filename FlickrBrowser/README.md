# Flickr Photo Browser App

## Overview

Welcome to the Flickr Photo Browser App! This Android application was created as a learning project to explore and implement various features in Android development. The app allows users to browse and discover photos from Flickr in a user-friendly interface. It is built using Kotlin, follows the MVVM (Model-View-ViewModel) architecture, and leverages modern libraries such as Kotlin Coroutines, Retrofit, Moshi, and Picasso for efficient asynchronous operations, network requests, JSON parsing, and image loading, respectively.

## Features

- **Photo Browsing**: Users can explore a vast collection of photos from Flickr in a seamless and intuitive manner.

- **MVVM Architecture**: The app follows the MVVM architecture pattern, promoting separation of concerns and maintainability.

- **Kotlin Coroutines**: Asynchronous tasks, such as network requests, are handled using Kotlin Coroutines, ensuring smooth and responsive user interactions.

- **Retrofit**: The app utilizes Retrofit to retrieve data from the Flickr API. Retrofit simplifies the process of making network requests and handling responses.

- **Moshi: Moshi is employed for parsing the Flickr API responses, enabling efficient extraction of relevant information.

- **Picasso**: Picasso is used for converting image URLs into actual images. It simplifies the process of image loading, caching, and displaying, providing a smooth experience for users.

## Getting Started

To build and run the app locally, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/flickr-photo-browser.git
   ```

2. Open the project in Android Studio.

3. Build and run the app on an emulator or a physical device.

## Dependencies

The app relies on the following key dependencies, which are managed through Gradle:

- [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines): For asynchronous programming.
- [Retrofit](https://github.com/square/retrofit): For making network requests.
- [Moshi](https://github.com/square/moshi): For parsing JSON responses from the Flickr API.
- [Picasso](https://github.com/square/picasso): For loading and displaying images efficiently.

Make sure to check the `build.gradle` files for the latest versions of these dependencies.

## Acknowledgments

- Special thanks to the [Flickr API](https://www.flickr.com/services/api/) for providing access to a vast collection of photos.
- The development team acknowledges the open-source community and the contributors of the libraries used in this project.

Happy browsing! 📷
