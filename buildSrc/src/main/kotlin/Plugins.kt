import versions.LibrariesVersions

object Plugins {
    const val android = "com.android.tools.build:gradle:${PluginVersions.buildTools}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${LibrariesVersions.kotlinVersion}"
}
