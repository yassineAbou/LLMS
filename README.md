<div align="center">  
 <img src="https://i.imgur.com/aBVZRPL.png" alt="LLMS" style="width: 100px; height: 100px; object-fit: contain; margin-right: 10px;">
 <h1 style="display: inline-block; margin: 0; vertical-align: middle; text-align: center; width: 100%;">LLMS</h1>  
</div>

<p align="center">
<img src="https://img.shields.io/badge/AI%20Horde-FF2222?logo=ai" alt="AI Horde">
<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white" alt="Kotlin">
<img src="https://img.shields.io/badge/Kotlin%20Multiplatform-7F52FF?style=flat&logo=kotlin&logoColor=white" alt="Kotlin Multiplatform">
<img src="https://img.shields.io/badge/Compose%20Multiplatform-4285F4?logo=jetpack-compose&logoColor=white" alt="Compose Multiplatform">
<img src="https://img.shields.io/badge/Ktor-0099FF?logo=ktor&logoColor=white" alt="Ktor">
<img src="https://img.shields.io/badge/WebAssembly-654FF0?logo=webassembly&logoColor=white" alt="WebAssembly">
<img src="https://img.shields.io/badge/Android-3DDC84?logo=android" alt="Android">
<img src="https://img.shields.io/badge/iOS-000000?logo=apple" alt="iOS">
<img src="https://img.shields.io/badge/Linux-FCC624?logo=linux&logoColor=black" alt="Linux">
<img src="https://img.shields.io/badge/Windows-0078D6?logo=windows" alt="Windows">
<img src="https://img.shields.io/badge/macOS-808080?logo=apple" alt="macOS">
</p>

Discover the power of AI with our Kotlin Multiplatform app. Choose from the latest open-source text and image models to boost your creativity. Pick the model that fits you, create unique texts or images.

## Releases
### ✅ Available Releases
- **Android app**
   
  Download the latest version directly from the Release section

- **WebAssembly**
  
  Check the about section

- **Desktop(macOS/Windows/Linux)**:
  
  1- Build the release by running this command:  
     ```bash
     ./gradlew packageReleaseDistributionForCurrentOS
     ```
  2- Find binaries in:  
     ```bash
     composeApp/build/compose/binaries/
     ```
### ⌛ Pending Releases
- **iOS**:
  
  *Coming soon after Apple Developer account creation*


## 🎨 Demo
https://github.com/user-attachments/assets/6f407768-673f-4d4e-9382-eba3baa88875

https://github.com/user-attachments/assets/ce9c5052-f51b-41aa-bbbd-2fff38bb5035

## 🤖 Horde AI Integration

Horde AI is a powerful, crowdsourced platform that combines a distributed cluster of image and text
generation workers. For more details, visit their [website](https://stablehorde.net/).

To interact with the models provided by Horde AI, the application uses the Horde AI REST API.
For more information on how to use this API, please visit
the [API documentation](https://stablehorde.net/api/).

## 📈 Progress

Our development process is divided into three key steps:

#### 1. Prototype with Fake Data 🚧

- Status: 🎯 Done
- Building a basic prototype with fake data to test the app's structure and functionality.

#### 2. Connect with Horde AI REST API 🌐

- Status: 🔄 in progress
- Integrating the app with Horde AI's REST API to enable AI-powered features and data processing.


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
- [Koin](https://insert-koin.io/docs/reference/koin-compose/compose/) (Dependency injection)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) (asynchronous or non-blocking)
- [Sketch](https://github.com/panpf/sketch) (Image loading)

## Package Structure
```  
app/
├── core/
│   ├── di
│   ├── navigation
│   ├── sharedViews
│   ├── theme
│   └── util
└── feature/
    ├── chat/
    │   ├── model
    │   └── ui/
    │       ├── chat
    │       ├── history
    │       ├── listDetailPane
    │       └── view
    ├── imagine/
    │   ├── model
    │   └── ui/
    │       ├── supportingPane
    │       ├── util
    │       └── view
    
``` 

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
