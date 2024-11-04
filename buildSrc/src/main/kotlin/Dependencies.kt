object DefaultConfig {
    const val appId = "com.example.posterr"
    const val minSdk = 23
    const val targetSdk = 33
    const val compileSdk = 33
}

object Modules {
    const val home = ":features:home"
    const val profile = ":features:profile"
    const val models = ":core:models"
    const val domain = ":core:domain"
    const val data = ":core:data"
    const val navigation = ":core:navigation"
    const val ui = ":core:ui"
}

object Versions {

    const val KOTLIN_VERSION = "1.5.0"
    const val COROUTINES_VERSION = "1.5.0"
    const val LIFECYCLE_VERSION = "2.5.1"

    const val RETROFIT_VERSION = "2.9.0"
    const val MOSHI_VERSION = "1.13.0"
    const val TEST_MOCKK_VERSION = "1.11.0"
    const val TEST_JUNIT_VERSION = "4.13.2"
    const val TEST_JUNIT_EXT = "1.1.3"
    const val TEST_ESPRESSO = "3.4.0"

    const val ANDROIDX_CORE_VERSION = "1.9.0"
    const val ANDROIDX_APP_COMPAT_VERSION = "1.5.1"
    const val GOOGLE_MATERIAL_VERSION = "1.4.0"
    const val ANDROIDX_CONSTRAINT_LAYOUT_VERSION = "2.0.4"

    const val DI_KOIN_VERSION = "3.0.2"
    const val ROOM_VERSION = "2.4.3"
}

object Libs {
    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}"

    // Coroutines
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_VERSION}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"

    const val androidxCore = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_VERSION}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APP_COMPAT_VERSION}"
    const val googleMaterial = "com.google.android.material:material:${Versions.GOOGLE_MATERIAL_VERSION}"
    const val androidXConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.ANDROIDX_CONSTRAINT_LAYOUT_VERSION}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"

    const val moshi = "com.squareup.moshi:moshi:${Versions.MOSHI_VERSION}"
    const val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI_VERSION}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT_VERSION}"

    const val room = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"
    const val roomKapt = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"

    // DI Koin
    const val koinAndroidx = "io.insert-koin:koin-android:${Versions.DI_KOIN_VERSION}"
    const val koinCore = "io.insert-koin:koin-core:${Versions.DI_KOIN_VERSION}"

    // Fragments
    const val viewModels = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VERSION}"
    const val viewModelJ = "androidx.lifecycle:lifecycle-viewmodel:${Versions.LIFECYCLE_VERSION}"
    const val lifecycleLivedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_VERSION}"

    // ||
    // TESTING
    // ||

    // Mockk
    const val testMockk = "io.mockk:mockk:${Versions.TEST_MOCKK_VERSION}"

    // Junit 4
    const val junit4 = "junit:junit:${Versions.TEST_JUNIT_VERSION}"
    const val junitExt = "androidx.test.ext:junit:${Versions.TEST_JUNIT_EXT}"

    // Espresso
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.TEST_ESPRESSO}"

    const val testCore = "androidx.arch.core:core-testing:2.1.0"
}