plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(project(Modules.models))
    implementation(Libs.koinCore)
    implementation(Libs.coroutinesCore)
    implementation(Libs.junit4)
    testImplementation(Libs.testMockk)
}