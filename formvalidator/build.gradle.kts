import versions.ProjectVersion

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    defaultConfig {
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

withLibraries(
    Libs.appCompat,
    Libs.rxJava,
    Libs.rxJava2,
    Libs.rxAndroid,
    Libs.mviCore,
    Libs.rxBridge
)