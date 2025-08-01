
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
- **iOS**:
  
  *Run iOS emulator via Xcode, Intellij Idea or Android Studio*


## 🎨 Demo

https://github.com/user-attachments/assets/af1903df-139f-4178-8fcb-c3d7c7b46219

https://github.com/user-attachments/assets/46d00b90-6df8-484c-bfd6-f38c65ad85d9

https://github.com/user-attachments/assets/144247db-4e89-4cd4-94d6-77fc20119146

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
- [Koin](https://insert-koin.io/docs/reference/koin-compose/compose/) (Dependency injection)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) (asynchronous or non-blocking)
- [Sketch](https://github.com/panpf/sketch) (Image loading)
- [Ktor Client](https://ktor.io/docs/full-stack-development-with-kotlin-multiplatform.html) (HTTP client)
- [Markdown renderer](https://github.com/mikepenz/multiplatform-markdown-renderer) (Markdown renderer)
- [Drag Select](https://github.com/jordond/drag-select-compose) (adding Google Photos style drag-to-select multi-selection to a LazyGrid)
- [SQLDelight](https://sqldelight.github.io/sqldelight/2.1.0/) (SQLDelight generates typesafe Kotlin APIs from your SQL statements.)

## Package Structure
```  
app/  
├── core/
│   ├── data
│   │   ├── local
│   │   └── remote
│   ├── di  
│   ├── navigation  
│   ├── sharedViews  
│   ├── theme  
│   └── util  
└── feature/  
    ├── chat/  
    │   ├── data  
    │   │   └── model  
    │   └── ui  
    │       ├── chat  
    │       ├── history  
    │       ├── listDetailPane  
    │       └── view  
    ├── imagine/  
    │   ├── data  
    │   │   └── model  
    │   └── ui  
    │       ├── supportingPane  
    │       ├── util  
    │       └── view
    └──You/  
    
    
    
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
