# Posterr - Rodrigo Calderano Barbacovi

**Posterr is a simple project with some resemblance to Twitter but with far fewer features.**

**Key Features**:
- Post Creation: Users can create original posts, repost from others, and quote existing posts.
- Home Screen: Displays a feed of diverse posts from all users, allowing for easy interaction and discovery.
- Profile Screen: Showcases user data, including personal information and a count of each post type (original, repost, quote).
- Online and Offline Functionality: The app operates primarily online but includes local database persistence to ensure users can access and create posts without an internet connection.

**Additional Usage Rules**:
- Users can post from either the Home or Profile screen.
- Each user is limited to creating a maximum of 5 posts per day.

## Project Structure

This project structure is based on modularization, follows Clean architecture principles and uses MVVM pattern for presentation layer.

## Modules

Follows [Dependency-Rule](https://khalilstemmler.com/wiki/dependency-rule/) from Clean arch, meaning that domain layer is completely agnostic of other modules and feature modules are not dependent on each other.
![WhatsApp Image 2024-11-04 at 16 05 35](https://github.com/user-attachments/assets/f77f282b-93c4-4905-bb97-4dca30b6ff3c)

- app module: Higher level module that controls DI
- buildSrc: Controls lib versions
- core:data: Represents the data layer containing repository implementation, local and remote data sources, dao and network resources
- core:domain: Kotlin module containing common use cases used by multiple features and repository interfaces
- core:models: Kotlin module containing common models
- core:navigation: Containing utility resources for navigation
- core:ui: Represents the design system, containing ui components, recycler view adapters, view holders and common xml files
- features:home: Represent the Home Feature, including the presentation layer and some specific domain use cases
- features:profile: Represent the Profile Feature, including the presentation layer and some specific domain use cases

## Automated Tests

- Instrumented UI tests: Uses [Espresso](https://developer.android.com/training/testing/espresso) and [Robot Pattern](https://jakewharton.com/testing-robots/). Example can be found on: HomeUiTest
- Unit tests: Uses [MockK](https://mockk.io/) and [GivenWhenThen Pattern](https://martinfowler.com/bliki/GivenWhenThen.html). Example can be found on: AddPostUseCaseImplTest. *There are others classes that still need unit test development*

## Development setup

Require the latest Android Studio to be able to build the app. (Version used to development: Chipmunk)

To run the App you need a device with android API level of 23 or higher 

### Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/) and [Flow](https://kotlinlang.org/docs/flow.html)
- Uses [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- Uses [Room](https://developer.android.com/jetpack/androidx/releases/room) for local persistence
- Uses [Retrofit](https://square.github.io/retrofit/) for HTTP client
- Uses [JUnit4](https://developer.android.com/training/testing/junit-rules), [Espresso](https://developer.android.com/training/testing/espresso), [Fragment Tests](https://developer.android.com/guide/fragments/test) among other libraries for unit & instrumented tests.

## Critique 1 - What needs to be improved

**Architecture**
- The app module is currently coupled with some UI features, but it should only be responsible for setting up the application, instantiating dependency injection (DI), and the necessary tools.
- The core:data, core:domain and core:model are too generic and have the potential to expand indefinitely. A better approach would be to create separate common modules for each scope as needed (such as PostEngineModule).
- To further decouple the modules, a valuable improvement would be to separate each module into public and implementation versions. This would enhance modularity and build performance during maintenance.

**UI**
- Improve UI using a more appropriate design
- Add support for jetpack compose

**Tests**
- Add integration tests for the data layer to ensure proper functionality and guarantee accurate deserialization.
- Implement unit tests of the remaining layers and add a code coverage guardrail

## Critique 2 - How to handle app growth

To tackle the app growth, implementing a paging strategy will definitely be needed. One library that could be used is [Paging](https://developer.android.com/topic/libraries/architecture/paging)

Still on the app growth thought, Feature Flag and Analytics Tracking strategies would also be appropriate to collect data, monitor and make business decisions.

If the application's code base and development team grows and the build time increases, changing from gradle to buck or bazel should be considered.

To ensure good performance on all devices, the following strategies would help the app to run more optimally:

- Implement [Baseline profiles](https://developer.android.com/topic/performance/baselineprofiles/overview) to improve performance 
- Change koin configuration to use [Scopes](https://insert-koin.io/docs/reference/koin-core/scopes) to load the dependencies only on the flow they are needed
- Develop new features with [Don't keep activities] options turned on and a memory leak tool to assure correct ram allocation, such as [Leak Canary]([https://insert-koin.io/docs/reference/koin-core/scopes](https://square.github.io/leakcanary/))

## Critique 3 - Tradeoff decisions

**Koin**: 
- Pros: Easy setup, readable syntax, lightweight and fast build performance
- Cons: As it is based on a Service locator pattern it may may catch exceptions on runtime. It has weak type safety through compile-time checks when compared to Dagger/Hilt.

**MVVM**:
- Pros: Clear separation of concerns, widely Used, Google-Backed Architecture, usage of observable Pattern to decouple ViewModels from Views
- Cons: When compared to MVI, MVVM may involve more complex state management, bidirectional data flow and ViewModels from MVVM may grow larger as MVI has more layers to handle data flow such as Intent Interpreters/Handlers/Proccesors/Reducers and Guards.

**Modularization**:
- Pros: Better organization, easier maintenance, parallel development, build time performance, code check performance, dynamic feature possibility.
- Cons: Increased complexity, circular dependency risk, time-consuming setup.

