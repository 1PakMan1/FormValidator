import versions.ProjectVersion

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    defaultConfig {
        applicationId = "ru.parkman.formvalidator"
        minSdkVersion(ProjectVersion.minSdkVersion)
        targetSdkVersion(ProjectVersion.compileSdkVersion)
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        versionName = Build.getVersionName(project)
        versionCode = Build.getVersionCode(project)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":formvalidator"))
}

withLibraries(
    Libs.kotlinStdLib,
    Libs.lifecycle,
    Libs.appCompat,
    Libs.rxJava,
    Libs.rxJava2,
    Libs.rxAndroid,
    Libs.rxBinding,
    Libs.Ui.material,
    Libs.Ui.constraintLayout,
    Libs.mviCore,
    Libs.mviCoreAndroid,
    Libs.ktxCore
)
