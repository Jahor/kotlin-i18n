buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
//        classpath("dev.icerock.moko:kswift-gradle-plugin:0.7.0")
    }
}

plugins {
    kotlin("multiplatform") version "2.0.0"
    id("com.android.library") version "8.2.2"
    id("maven-publish")
    kotlin("plugin.serialization") version "2.0.0"
//    id("dev.icerock.moko.kswift") version "0.7.0"
    id("co.touchlab.skie") version "0.8.1"
}

group = "info.leonenko"
version = "1.0.18"

val frameworkName = "i18n"

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvmToolchain(8)
    androidTarget()
    jvm() /*{
        jvmToolchain(8)
    }*/

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosX64(),
        macosArm64(),
        watchosArm32(),
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
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.3")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
        }
    }
}

android {
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    namespace = "info.leonenko.i18n"
}