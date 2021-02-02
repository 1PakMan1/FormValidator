import versions.LibrariesVersions

object Libs {
    const val appCompat = "androidx.appcompat:appcompat:${LibrariesVersions.support}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${LibrariesVersions.kotlinVersion}"
    const val ktxCore = "androidx.core:core-ktx:${LibrariesVersions.ktx}"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibrariesVersions.lifecycle}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${LibrariesVersions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${LibrariesVersions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${LibrariesVersions.lifecycle}"
    const val lifecycleJava8 = "androidx.lifecycle:lifecycle-common-java8:${LibrariesVersions.lifecycle}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${LibrariesVersions.fragment}"
    const val mviCore = "com.github.badoo.mvicore:mvicore:${LibrariesVersions.mviCore}"
    const val mviCoreAndroid = "com.github.badoo.mvicore:mvicore-android:${LibrariesVersions.mviCore}"
    const val lifecycle = "androidx.lifecycle:lifecycle-common-java8:${LibrariesVersions.lifecycle}"

    // Rx
    const val rxJava = "io.reactivex.rxjava3:rxjava:${LibrariesVersions.rxJava}"
    const val rxJava2 = "io.reactivex.rxjava2:rxjava:${LibrariesVersions.rxJava2}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${LibrariesVersions.rxAndroid}"
    const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${LibrariesVersions.rxKotlin}"
    const val rxBridge = "com.github.akarnokd:rxjava3-bridge:${LibrariesVersions.rxBridge}"
    const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:${LibrariesVersions.rxBinding}"
    const val rxBindingAppCompat = "com.jakewharton.rxbinding4:rxbinding-appcompat:${LibrariesVersions.rxBinding}"
    const val rxSharedPreferences = "com.f2prateek.rx.preferences2:rx-preferences:${LibrariesVersions.rxSharedPreferences}"

    object Ui {
        const val adapterDelegate = "com.hannesdorfmann:adapterdelegates4:${LibrariesVersions.adapterDelegate}"
        const val adapterDelegateLayoutContainer = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-layoutcontainer:${LibrariesVersions.adapterDelegate}"
        const val appCompat = "androidx.appcompat:appcompat:${LibrariesVersions.appCompat}"
        const val material = "com.google.android.material:material:${LibrariesVersions.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${LibrariesVersions.constraintLayout}"
    }
}
