import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.2.21"
    application
}

group = "at.downloadpizza"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.kittinunf.fuel", "fuel", "2.3.1")
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.10.2")
}

application {
    mainClass.set("MainKt")
}