# Posterr

Posterr is a simple project with some resemblance to Twitter but with far fewer features.

## Project Structure

This project structure is based on modularization, follows Clean Arch principles and uses MVVM patter for presentation layer.

## Modules

- app module: Higher level module that controls DI
- buildSrc: Controls lib versions
- core:data: Represents the data layer containing repository implementation, local and remote data sources, dao and network resources
- core:domain: Kotlin module containing common use cases used by multiple features and repository interfaces
- core:models: Kotlin module containing common models
- core:navigation: Containing utility resources for navigation
- core:ui: Represents the design system, containing recycler view adapters, view holders and common xml files
- features:home: Represent the Home Feature, including the presentation layer and some specific domain use cases
- features:profile: Represent the Profile Feature, including the presentation layer and some specific domain use cases

## Automated Tests

- Instrumented UI tests: Uses [Robot Pattern](https://jakewharton.com/testing-robots/). Example can be found on: HomeUiTest and ProfileUiTest
- Unit tests: Uses [GivenWhenThen Pattern](https://martinfowler.com/bliki/GivenWhenThen.html). Example can be found on: AddPostUseCaseImplTest. There are others classes that still need unit test development

## Development setup

You require the latest Android Studio to be able to build the app. (Version used: Chipmunk)
To run the App you need an emulator or device with android API level of 23 or higher 

### Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/) and [Flow](https://kotlinlang.org/docs/flow.html)
- Uses [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- Uses [JUnit4](https://developer.android.com/training/testing/junit-rules), [Espresso](https://developer.android.com/training/testing/espresso), [Fragment Tests](https://developer.android.com/guide/fragments/test) among other libraries for unit & instrumented tests.

