# README

[🇺🇸 English](#english) | [🇪🇸 Español](#español)

## English

# Kotlin Multiplatform App Example

This repository contains an example of a Kotlin Multiplatform (KMP) application.

This App supports both Android and iOS platforms. Though the same code could be extended to support other platforms like Web or Desktop.

The project demonstrates the use of various Android libraries that currently support multiplatform development.

It's been developed fully in this course that you can find on YouTube (in Spanish): TBA

### Code structure

This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)

### Technologies Used

- [Kotlin 2.0](https://devexpert.io/migrar-kotlin-2/)
- [Gradle using Kotlin DSL and Versions Catalog](https://youtu.be/HrLB3iQzD-k)
- Native Swift UI for iOS and Compose for Android (in [main branch](https://github.com/devexpert-io/kmp-movies/tree/main))
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) for UI (in [compose branch](https://github.com/devexpert-io/kmp-movies/tree/compose))
- [Coil](https://coil-kt.github.io/coil/upgrading_to_coil3/#multiplatform) for image loading
- [Navigation Compose](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html) for navigation
- [ViewModels](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html) from Architecture Components 
- [Ktor Client](https://devexpert.io/ktor-client-android/) for networking
- [Room](https://devexpert.io/room-en-kotlin-multiplatform/) for local persistence
- [Koin](https://devexpert.io/koin-kotlin-multiplatform/) for dependency injection
- [Moko permissions](https://github.com/icerockdev/moko-permissions) for handling permissions
- [Skie](https://skie.touchlab.co/features/flows-in-swiftui) for Flows support in Swift

### Running the Project

To run the project, you will need an API key from [TheMovieDb](https://www.themoviedb.org/settings/api). This key should be defined in your `local.properties` file in the root of your project. The property should be defined as follows:

```properties
API_KEY= your_api_key_here
```
Replace `your_api_key_here` with your actual API key.

---

## Español

# Ejemplo de App en Kotlin Multiplatform

Este repositorio contiene un ejemplo de una aplicación en Kotlin Multiplatform (KMP).

Esta App soporta tanto las plataformas Android como iOS. Aunque el mismo código podría extenderse para soportar otras plataformas como Web o Desktop.

El proyecto demuestra el uso de varias librerías de Android que actualmente soportan el desarrollo multiplataforma.

Ha sido desarrollado completamente en este curso que puedes encontrar en YouTube: TBA

### Estructura del código

Este es un proyecto Kotlin Multiplatform que tiene como objetivo Android e iOS.

* `/composeApp` es para el código que se compartirá en tus aplicaciones Compose Multiplatform.
  Contiene varias subcarpetas:
    - `commonMain` es para el código que es común para todos los objetivos.
    - Otras carpetas son para el código Kotlin que se compilará solo para la plataforma indicada en el nombre de la carpeta.
      Por ejemplo, si deseas usar CoreCrypto de Apple para la parte de iOS de tu aplicación Kotlin,
      `iosMain` sería la carpeta correcta para esas llamadas.

* `/iosApp` contiene aplicaciones iOS. Incluso si estás compartiendo tu UI con Compose Multiplatform,
  necesitas este punto de entrada para tu aplicación iOS. Aquí también es donde debes agregar el código SwiftUI para tu proyecto.

Aprende más sobre [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)...

### Tecnologías Utilizadas

- [Kotlin 2.0](https://devexpert.io/migrar-kotlin-2/)
- [Gradle utilizando Kotlin DSL y Catálogo de Versiones](https://youtu.be/HrLB3iQzD-k)
- Native Swift UI para iOS y Compose para Android (en la [rama main](https://github.com/devexpert-io/kmp-movies/tree/main))
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) para la interfaz de usuario (en la [rama compose](https://github.com/devexpert-io/kmp-movies/tree/compose))
- [Coil](https://coil-kt.github.io/coil/upgrading_to_coil3/#multiplatform) para la carga de imágenes
- [Navigation Compose](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html) para la navegación
- [ViewModels](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html) de los Componentes de Arquitectura
- [Ktor Client](https://devexpert.io/ktor-client-android/) para la red
- [Room](https://devexpert.io/room-en-kotlin-multiplatform/) para la persistencia local
- [Koin](https://devexpert.io/koin-kotlin-multiplatform/) para la inyección de dependencias
- [Moko permissions](https://github.com/icerockdev/moko-permissions) para el manejo de permisos
- [Skie](https://skie.touchlab.co/features/flows-in-swiftui) para el soporte de Flows en Swift

### Ejecutando el Proyecto

Para ejecutar el proyecto, necesitarás una clave API de [TheMovieDb](https://www.themoviedb.org/settings/api). Esta clave debe definirse en tu archivo `local.properties` en la raíz de tu proyecto. La propiedad debe definirse de la siguiente manera:

```properties
API_KEY= tu_clave_api_aquí
```
Reemplaza `tu_clave_api_aquí` con tu clave API real.

## License

    Copyright 2024 DevExpert

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.