plugins {
    id("org.jetbrains.kotlin.kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = DefaultConfig.compileSdk

    defaultConfig {
        minSdk = DefaultConfig.minSdk
        targetSdk = DefaultConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://my.base.url\""
            )
        }
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://my.base.url\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Libs.room)
    implementation(Libs.roomKtx)
    implementation(Libs.retrofit)
    implementation(Libs.moshi)
    implementation(Libs.moshiConverter)
    kapt(Libs.roomKapt)
    kapt(Libs.moshiKapt)
    implementation(project(Modules.models))
    implementation(Libs.androidxCore)
    implementation(Libs.koinAndroidx)
    implementation(Libs.androidxAppCompat)
    implementation(Libs.googleMaterial)
    implementation(Libs.androidXConstraintLayout)
}