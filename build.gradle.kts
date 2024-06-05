buildscript {
    val composeVersion by extra("1.3.0") // Asegúrate de que esta versión es compatible con tu versión de Kotlin
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}