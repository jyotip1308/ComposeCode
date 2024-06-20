// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}

buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.2") // Check for the latest version
        classpath("com.google.gms:google-services:4.4.2") // Check for the latest version
    }
}