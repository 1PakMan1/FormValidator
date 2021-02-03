buildscript {
    val kotlin_version by extra("1.3.72")

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Plugins.android)
        classpath(Plugins.kotlin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
    }
}

val clean by tasks.creating(Delete::class) {
    delete = setOf(rootProject.buildDir)
}
