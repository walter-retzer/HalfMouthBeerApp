plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.1").apply(false)
    id("com.android.library").version("8.0.1").apply(false)
    id("org.jetbrains.compose").version("1.4.0").apply(false)
    kotlin("jvm").version("1.8.20").apply(false)
    kotlin("android").version("1.8.20").apply(false)
    kotlin("multiplatform").version("1.8.20").apply(false)
}

buildscript {
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
        classpath("dev.icerock.moko:resources-generator:0.22.3")
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.20.2")
    }
}

allprojects {
    apply(plugin = "kotlinx-atomicfu")
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
