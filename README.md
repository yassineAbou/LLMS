<div align="center">  
 <img src="https://imgur.com/9atzLry.png" alt="LLMS" style="width: 100px; height: 100px; object-fit: contain; margin-right: 10px;">
 <h1 style="display: inline-block; margin: 0; vertical-align: middle; text-align: center; width: 100%;">LLMS</h1>  
</div>

<p align="center">
<img src="https://img.shields.io/badge/AI Horde-DC143C?logo=ai" alt="AI Horde">
<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white" alt="Kotlin">
<img src="https://img.shields.io/badge/Kotlin%20Multiplatform-7F52FF?style=flat&logo=kotlin&logoColor=white" alt="Kotlin Multiplatform">
<img src="https://img.shields.io/badge/Compose%20Multiplatform-3b71e8?logo=jetpack-compose&logoColor=white&color=3b71e8" alt="Compose Multiplatform">
<img src="https://img.shields.io/badge/Ktor-00BFFF?logo=ktor&logoColor=white" alt="Ktor">
<img src="https://img.shields.io/badge/Swift-FA7343?logo=swift&logoColor=white&color=FA7343" alt="Swift">
<img src="https://img.shields.io/badge/Swift%20UI-FA7343?logo=swift&logoColor=white&color=blue" alt="Swift UI">
<img src="https://img.shields.io/badge/Android%20Studio-A4C639?logo=android-studio&logoColor=white" alt="Android Studio" style="background-color:#A4C639;">
<img src="https://img.shields.io/badge/Xcode-007ACC?logo=xcode&logoColor=white" alt="Xcode" style="background-color:#007ACC;">
  <img src="https://img.shields.io/badge/Android-3DDC84?logo=android" alt="Android">
  <img src="https://img.shields.io/badge/iOS-000000?logo=apple" alt="iOS">
  <img src="https://img.shields.io/badge/Linux-FCC624?logo=linux&logoColor=black" alt="Linux">
  <img src="https://img.shields.io/badge/Windows-0078D6?logo=windows" alt="Windows">
<img src="https://img.shields.io/badge/macOS-C0C0C0?logo=apple" alt="macOS">


Discover the power of AI with our Kotlin Multiplatform app, where you can choose from many text and image models to boost your creativity. Pick the model that fits you, create unique texts or images, and keep everything safe and in sync on your devices

## 🌟 Features
⚠️ **Note:** New models provided by Horde AI will be added as they become available. This ensures that the app remains up-to-date with the latest advancements in AI technology.

### 📝 Text LLM Models
- **Default Model:** The most advanced open source text model is chosen by default for generating text.
-  **Selection:** Choose from a diverse range of  open-source text models.
- **Customization:** Enhance your experience by setting the maximum token context (512 to 2048 tokens) and controlling the output length (from 16 to 128 tokens).

### 🖼️ Image Models
- **Default Model:** The latest advanced generalistic image model is set as the default option for image generation.
- **Selection:** Explore image models across categories like Generalist, Anime, Furry, and Artistic.
- **Customization:** Generate images using text prompts or existing images. Adjust settings such as width, height, and apply specific filters like NSFW or exclude certain elements using negative prompts.

### 💾 Data Management
- **Storage:** All generated texts and images are securely saved on your device.
- **Synchronization:** Log in to synchronize your data across devices, ensuring no loss of data even if the app is uninstalled.


## 🤖 Horde AI Integration

Horde AI is a powerful, crowdsourced platform that combines a distributed cluster of image and text generation workers. For more details, visit their [website](https://stablehorde.net/).

To interact with the models provided by Horde AI, the application utilizes the Horde AI REST API. For more information on how to use this API, please visit the [API documentation](https://stablehorde.net/api/).

## 📚 Available Models

-   **Text Models**: Access a wide range of text models  [here](https://github.com/Haidra-Org/AI-Horde-text-model-reference).
-   **Image Models**: Explore  image models across various categories  [here](https://github.com/Haidra-Org/AI-Horde-image-model-reference)


## 🎮 Interactive Examples

⚠️ **Note:** The IDs shown in the videos are for demonstration purposes only. Please visit and sign up at Horde AI to create your own unique ID.

-   **Chat with Llama 3-70B**: How would you use AI to help you be more creative every day?


https://github.com/yassineAbou/LLMS/assets/72861602/14542392-5765-4e7d-88e2-72b2219e5f60



-   **Generate Image using AlbedoBase XL (SDXL)**: a dog takes a selfie in the forest, in the style of fisheye effects, somber mood, strong facial expression, tilt shif.


https://github.com/yassineAbou/LLMS/assets/72861602/edfc27ab-aae7-49d7-993e-c42a2020e530



## 📈 Progress

Our development process is divided into three key steps:

#### 1. Prototype with Fake Data 🚧

-   Status: 🔜 In Progress
-   Building a basic prototype with fake data to test the app's structure and functionality.

#### 2. Connect with Horde AI REST API 🌐

-   Status: ⏰ Not Started
-   Integrating the app with Horde AI's REST API to enable AI-powered features and data processing.

#### 3. Implement User Authentication 🔒

-   Status: ⏰ Not Started
-   Securing the app with user authentication to enable personalized features and data.

## 🏗️ Architecture

- **User Interface**: Uses [Compose multiplatfrom](https://www.jetbrains.com/lp/compose-multiplatform/).

- **Layers**: All data handling, business logic, and presentation layers are implemented using [kotlin multiplatform](https://www.jetbrains.com/kotlin-multiplatform/)

- **backend**: The backend is developed using [Ktor framework](https://ktor.io/).

<p align="center">
  <img src="https://kotlinlang.org/docs/images/multiplatform-compose.svg" alt="Kotlin Multiplatform" width="500">
</p>


## 📦 Libraries

⚠️ **Note:** Libraries used will be listed here once the project is done

## 🤝 Contribution
We welcome contributions to our project! Please follow these guidelines when submitting changes:

- Report bugs and feature requests by creating an issue on our GitHub repository.
- Contribute code changes by forking the repository and creating a new branch.
- Ensure your code follows our coding conventions.
- Improve our documentation by submitting changes as a pull request.

Thank you for your interest in contributing to our project!

## 📜 License
```
Copyright 2024 Yassine Abou 
  
Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  
You may obtain a copy of the License at  
  
    http://www.apache.org/licenses/LICENSE-2.0  
  
Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License.
