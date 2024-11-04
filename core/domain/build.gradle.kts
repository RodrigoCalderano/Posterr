plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("info.solidsoft.pitest") version "1.9.0"
}

configure<info.solidsoft.gradle.pitest.PitestPluginExtension> {
    avoidCallsTo.set(setOf("kotlin.jvm.internal"))
    mutators.set(setOf("STRONGER"))
    targetClasses.set(setOf("com.example.domain.exampleformutation.*"))
    targetTests.set(setOf("*"))
    threads.set(Runtime.getRuntime().availableProcessors())
    outputFormats.set(setOf("XML", "HTML"))
    mutationThreshold.set(10)
    coverageThreshold.set(10)
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