buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

plugins {
    kotlin("multiplatform") version "2.1.10"
    id("com.android.library") version "8.7.3"
    id("maven-publish")
    kotlin("plugin.serialization") version "2.1.10"
    id("co.touchlab.skie") version "0.10.1"
}

group = "info.leonenko"
version = "1.0.26"

val frameworkName = "i18n"

repositories {
    google()
    mavenCentral()
}

skie {
    analytics {
        disableUpload.set(true)
    }
}

kotlin {
    jvmToolchain(11)
    androidTarget()
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosX64(),
        macosArm64(),
//        watchosArm32(),
        watchosArm64(),
        watchosSimulatorArm64(),
        watchosX64(),
        tvosArm64(),
        tvosSimulatorArm64(),
        tvosX64()
    ).forEach {
        it.binaries.framework {
            baseName = frameworkName
        }

        it.binaries.all {
            freeCompilerArgs = freeCompilerArgs + "-Xadd-light-debug=enable"
        }
    }

    linuxArm64()
    linuxX64()
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.8.0")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
        }
    }
}

android {
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    namespace = "info.leonenko.i18n"
}