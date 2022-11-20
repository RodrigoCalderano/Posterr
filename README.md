# Posterr

Posterr is a simple project with some resemblance to Twitter but with far fewer features.

## Project Structure

This project structure is based on modularization, follows Clean Arch principles and uses MVVM pattern for presentation layer.

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

- Instrumented UI tests: Uses [Robot Pattern](https://jakewharton.com/testing-robots/). Example can be found on: HomeUiTest
- Unit tests: Uses [GivenWhenThen Pattern](https://martinfowler.com/bliki/GivenWhenThen.html). Example can be found on: AddPostUseCaseImplTest. *There are others classes that still need unit test development*

## Development setup

You require the latest Android Studio to be able to build the app. (Version used: Chipmunk)
To run the App you need an emulator or device with android API level of 23 or higher 

### Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/) and [Flow](https://kotlinlang.org/docs/flow.html)
- Uses [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- Uses [JUnit4](https://developer.android.com/training/testing/junit-rules), [Espresso](https://developer.android.com/training/testing/espresso), [Fragment Tests](https://developer.android.com/guide/fragments/test) among other libraries for unit & instrumented tests.

### Critique

What that still need to be developed and improved:

- Improve the UI using a more appropriate design
- Restructure the post creation feature to a common presentation layer component inside core:ui
- Implement the rest of unit tests and increase the test coverage

To tackle the app growth, implementing a paging strategy will definitely be needed. One library that could be used is [Paging](https://developer.android.com/topic/libraries/architecture/paging)
Still on the app growth thought, Feature Flag and Tracking strategies would also be appropriate to to collect data and make business decisions.

To ensure performance on all devices, the following strategies would help the app to run more optimally:

Assuming the app get multiple crash reports and reviews saying the app is not working properly and is slow for specific models
- Implement [Baseline profiles](https://developer.android.com/topic/performance/baselineprofiles/overview) to improve performance 
- Change koin configuration to use [Scopes](https://insert-koin.io/docs/reference/koin-core/scopes) to load the dependencies only on the flow they are needed
- Develop new features with [Don't keep activities] options turned on and a memory leak tool to assure correct ram allocation
