# Movie App README

## Overview
This application is a movie app built using the MVVM (Model-View-ViewModel) architecture pattern. It utilizes Retrofit for network requests to fetch movie data from an API and displays it in a RecyclerView. The app is designed with a focus on presenting the data in a visually appealing and user-friendly manner, ensuring smooth scrolling and responsiveness. Additionally, features such as pagination are implemented to efficiently handle large datasets.

## Features
1. **Movie List Screen:**
   - Displays a list of movies fetched from the API.
   - Utilizes RecyclerView for smooth scrolling.
   - Implements pagination to efficiently handle large datasets.

2. **Data Details Screen:**
   - Upon selecting a movie from the list, navigates to a detailed screen displaying additional information about the selected movie.
   - Presents relevant details in a clear and organized manner.
   - Allows navigation back to the list screen.

3. **Additional Features (Optional):**
   - Implement search functionality to allow users to search and filter the data based on specific criteria.

## Setup Instructions
Follow these instructions to build and run the application:

### Prerequisites
- Android Studio installed on your machine.
- An Android device or emulator to run the application.

### Building the Application
1. Clone this repository to your local machine using Git:

   ```bash
   git clone https://github.com/itzz-laksh/MovieApp.git
   ```

2. Open Android Studio and select "Open an existing Android Studio project."

3. Navigate to the directory where you cloned the repository and select the project.

4. Let Android Studio sync the project and download any dependencies.

### Running the Application
1. Connect your Android device to your computer using a USB cable, or launch an emulator from Android Studio.

2. Ensure that USB debugging is enabled on your device.

3. Click on the "Run" button in Android Studio, or press Shift + F10, to build and run the application on your device or emulator.

4. Once the build process is complete, the application will be installed and launched automatically on your device or emulator.

## Acknowledgements
Special thanks to [Retrofit](https://square.github.io/retrofit/) for simplifying network requests and to the creators of the API used in this project.
