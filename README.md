
<div align="center">  
 <img src="https://imgur.com/mVICjDJ.png" alt="LLMS" style="width: 100px; height: 100px; object-fit: contain; margin-right: 10px;">
 <h1 style="display: inline-block; margin: 0; vertical-align: middle; text-align: center; width: 100%;">LLMS</h1>  
</div>

<p align="center">
<img src="https://img.shields.io/badge/%20AI-000000?logo=ai&logoColor=white&color=000000" alt="AI">
<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white" alt="Kotlin">
<img src="https://img.shields.io/badge/Kotlin%20Multiplatform-7F52FF?style=flat&logo=kotlin&logoColor=white" alt="Kotlin Multiplatform">
<img src="https://img.shields.io/badge/Compose%20Multiplatform-4285F4?logo=jetpack-compose&logoColor=white" alt="Compose Multiplatform">
<img src="https://img.shields.io/badge/Ktor-0099FF?logo=ktor&logoColor=white" alt="Ktor">
<img src="https://img.shields.io/badge/WebAssembly-654FF0?logo=webassembly&logoColor=white" alt="WebAssembly">
<img src="https://img.shields.io/badge/Android-3DDC84?logo=android" alt="Android">
<img src="https://img.shields.io/badge/iOS-000000?logo=apple" alt="iOS">
<img src="https://img.shields.io/badge/Desktop-FCC624?logo=desktop&logoColor=black" alt="Desktop">

</p>

Discover the power of AI with our Kotlin Multiplatform app. Choose from the latest open-source text and image models to boost your creativity. Pick the model that fits you, create unique texts or images. and keep everything safe and in sync across your devices.

## 🌸 Powered by Pollinations.ai

<p align="center">
  <a href="https://pollinations.ai">
    <img src="https://pollinations.ai/favicon.ico" alt="Pollinations.ai" width="60">
  </a>
</p>

This app uses [Pollinations.ai](https://pollinations.ai) — an open-source AI platform providing a unified API for text and image generation.

<p align="center">
  <a href="https://pollinations.ai"><img src="https://img.shields.io/badge/API-Pollinations.ai-FF6B6B?style=flat-square" alt="Pollinations.ai"></a>
  <a href="https://github.com/pollinations"><img src="https://img.shields.io/badge/Open%20Source-GitHub-181717?style=flat-square&logo=github" alt="GitHub"></a>
</p>

## 🚀 How to Run 
You can run the application for **Android**, **Desktop**, **WebAssembly**, and **iOS** directly from your IDE.

1. Open the project in **Android Studio** or **IntelliJ IDEA**.
2. Select your desired run configuration from the dropdown menu (e.g., `composeApp`, `desktopRun`, `iosApp`).
3. Click the **Run ▶️** button.

*For **iOS**, you can alternatively open the `iosApp` folder in **Xcode** and run it from there.*



## 🎨 Demo

https://github.com/user-attachments/assets/af1903df-139f-4178-8fcb-c3d7c7b46219

https://github.com/user-attachments/assets/46d00b90-6df8-484c-bfd6-f38c65ad85d9



https://github.com/user-attachments/assets/62ad62f5-a80a-4ac0-b8a7-f0707a45a249




## 📈 Progress

Our development process is divided into five key steps:

#### 1. Prototype with Fake Data 🚧
- Status: 🎯 Done

#### 2. Connect with AI REST API (Text Models) 📝 
- Status: 🎯 Done
  
#### 3. Connect with AI REST API (Image Models) 🖼️  
- Status: 🎯 Done 

#### 4. Save Chat History & Generated Images Locally 💾  
- Status: 🎯 Done  

#### 5. Sync Data Across Devices with Authentication 🔐  
- Status: 🔄 in progress  


## 🏗️ Architecture

- **User Interface**:
  Uses [Compose multiplatfrom](https://www.jetbrains.com/lp/compose-multiplatform/).

- **Layers**: All data handling, business logic, and presentation layers are implemented
  using [kotlin multiplatform](https://www.jetbrains.com/kotlin-multiplatform/)

- **backend**: The backend is developed using [Ktor framework](https://ktor.io/).  

<p align="center">
  <img src="https://miro.medium.com/v2/resize:fit:2552/1*0MUE4D4nlEITAUyOTZ1zcg.png" alt="Kotlin Multiplatform" width="250">
</p>

## 📦 Libraries

- [Material 3 Adaptive](https://www.jetbrains.com/help/kotlin-multiplatform-dev/whats-new-compose-170.html#across-platforms) (creates adaptive UIs that will adapt themselves automatically)
- [Compose Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html) (Navigates between composables with built-in infrastructure and features)
- [Kodein](https://github.com/kosi-libs/Kodein) (Painless Kotlin Dependency Injection)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) (asynchronous or non-blocking)
- [Sketch](https://github.com/panpf/sketch) (Image loading)
- [Ktor Client](https://ktor.io/docs/full-stack-development-with-kotlin-multiplatform.html) (HTTP client)
- [Markdown renderer](https://github.com/mikepenz/multiplatform-markdown-renderer) (Markdown renderer)
- [Drag Select](https://github.com/jordond/drag-select-compose) (adding Google Photos style drag-to-select multi-selection to a LazyGrid)
- [SQLDelight](https://sqldelight.github.io/sqldelight/2.1.0/) (SQLDelight generates typesafe Kotlin APIs from your SQL statements.)
- [KotlinMultiplatformAuth](https://github.com/sunildhiman90/KotlinMultiplatformAuth) (Authentification Library Targeting Android, iOS, Desktop and Web Kotlin/Js and Kotlin/Wasm both)
- [kotlinx-rpc](https://github.com/Kotlin/kotlinx-rpc) (is a Kotlin library for adding asynchronous Remote Procedure Call (RPC) services to your applications)
- [Exposed](https://github.com/JetBrains/Exposed) (Kotlin SQL Framework)

## Package Structure
```
LLMS/
├── composeApp/    #  Main application module for the UI, built with Compose Multiplatform
│   └── src/
│       ├── org/yassineabou/llms/app/
│       │   ├── App.kt
│       │   ├── MainScreen.kt
│       │   ├── core/
│       │   │   ├── data/
│       │   │   │   ├── async/
│       │   │   │   ├── local/
│       │   │   │   └── remote/
│       │   │   ├── di/
│       │   │   ├── navigation/
│       │   │   ├── sharedViews/
│       │   │   ├── theme/
│       │   │   └── util/
│       │   └── feature/
│       │       ├── chat/
│       │       │   ├── data/model/
│       │       │   └── ui/
│       │       │       ├── chat/
│       │       │       ├── history/
│       │       │       ├── listDetailPane/
│       │       │       └── view/
│       │       ├── imagine/
│       │       │   ├── data/model/
│       │       │   └── ui/
│       │       │       ├── supportingPane/
│       │       │       ├── util/
│       │       │       └── view/
│       │       └── you/
│       │           ├── model/
│       │           └── ui/
│       │               ├── util/
│       │               └── view/
│       └── sqldelight/org/yassineabou/llms/    # SQLDelight source files defining the local database schema
│
├── server/     #  Backend server module, using Ktor, handling API requests and database interactions.
│   └── src/
│       └── org/yassineabou/llms/
│           ├── Application.kt
│           ├── Environment.kt
│           └── database/
│               ├── tables/
│               ├── di/
│               ├── repository/
│               ├── service/
│               └── DatabaseFactory.kt
│
└── shared/    #  Shared API contract and data models, enabling type-safe communication between compose and server module.
    └── src/
        └── org/yassineabou/llms/api/
            ├── ChatService.kt
            ├── ImageService.kt
            ├── MessageService.kt
            ├── UserService.kt
            ├── Constants.kt
            └── Platform.kt
```
## 🔵🔴🟡🟢 Google Authentication Setup
This project supports Google Authentication for data syncing across devices (part of Step 5 in Progress). Keys and sensitive files (e.g., `google-services.json`, `GoogleService-Info.plist`) are hidden and not committed to GitHub for security. To enable real Google auth after cloning the repo, follow these platform-specific steps. We've included fake data in the code (e.g., in `commonMain/YouViewModel.kt`) for testing without setup—uncomment the real auth code when ready.

If you are confused in the process, watch this video: [https://youtu.be/-5Ws4HSaYJc?si=uHhDbmnhC7GBZEL3](https://youtu.be/-5Ws4HSaYJc?si=uHhDbmnhC7GBZEL3).

### General Steps
1. Go to the [Firebase Console](https://console.firebase.google.com/).
2. Create a new Firebase project (or select an existing one).
3. Enable **Authentication** → **Sign-in method** → **Google**.
4. For multiplatform support, also go to [Google Cloud Console → APIs & Services → Credentials](https://console.cloud.google.com/apis/credentials) to create OAuth 2.0 Client IDs as needed.

### Compose App
1. In Firebase, click “Add app” → **Android**.
2. Register your package name (e.g., `com.yassineabou.llms`), add SHA-1 fingerprints (debug/release).
3. Download `google-services.json`.
4. Replace the placeholder in `composeApp/google-services.json` with your file.
5. In Google Cloud Console, create an “Android” OAuth Client ID with the same package name and SHA-1.
6. In `androidMain/MainActivity.kt`, uncomment the init code for `KMAuthInitializer`.
7. In `commonMain/YouViewModel.kt` and `commonMain/App.kt`, uncomment the Google auth-related code.
8. Sync Gradle and rebuild.

### iOSMain Module
1. In Firebase, click “Add app” → **iOS**.
2. Register your bundle ID, App Store ID (optional), and nickname.
3. Download `GoogleService-Info.plist`.
4. Replace the placeholder in `iosApp/GoogleService-Info.plist` with your file.
5. Drag it into Xcode (check “Copy items if needed”).
6. Add the Google Sign-In SDK via Xcode: `File` → `Add Packages…` → URL: `https://github.com/google/GoogleSignIn-iOS` → Add **GoogleSignIn** to your target.
7. In `iosApp/Info.plist`, uncomment and paste:
   - `<key>GIDClientID</key><string>PASTE CLIENT_ID FROM GoogleService-Info.plist HERE</string>`
   - For `<key>CFBundleURLTypes</key>`, add the `<dict>` with `<key>CFBundleURLSchemes</key><array><string>PASTE REVERSED_CLIENT_ID FROM GoogleService-Info.plist HERE</string></array>`.

### Desktop/WebAssembly (desktopMain Module and commonMain)
1. In Google Cloud Console, create a “Web application” OAuth Client ID.
2. Add authorized redirect URIs (e.g., `http://localhost:8080/callback`).
3. Copy the generated `CLIENT_ID` and `CLIENT_SECRET`.
4. Paste them into `commonMain/GoogleOAuthConfig.kt` (replace the empty constants).
5. In `desktopMain/main.kt`, uncomment the init code for `KMAuthInitializer.initClientSecret`.
6. In `commonMain/App.kt` and `commonMain/YouViewModel.kt`, uncomment the Google auth-related code.


## 🤝 Contribution

We welcome contributions to our project! Please follow these guidelines when submitting changes:

- Report bugs and feature requests by creating an issue on our GitHub repository.
- Contribute code changes by forking the repository and creating a new branch.
- Ensure your code follows our coding conventions.
- Improve our documentation by submitting changes as a pull request.

Thank you for your interest in contributing to our project!

## 📜 License

```
Copyright 2025 Yassine Abou 
  
Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  
You may obtain a copy of the License at  
  
    http://www.apache.org/licenses/LICENSE-2.0  
  
Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License.
