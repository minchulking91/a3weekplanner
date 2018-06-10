object Versions {
    const val anko = "0.10.5"
    const val androidx = "1.0.0-alpha1"
    const val dagger = "2.11"
    const val junit = "4.12"
    const val espresso = "3.0.1"
    const val retrofit = "2.3.0"
    const val okhttp_logging_interceptor = "3.9.0"
    const val mockito = "2.9.0"
    const val mockito_all = "1.10.19"
    const val constraint_layout = "1.1.0"
    const val glide = "4.5.0"
    const val timber = "4.5.1"
    const val android_gradle_plugin = "3.2.0-alpha15"
    const val rx_android = "2.0.2"
    const val rxkotlin2 = "2.2.0"
    const val gson = "2.8.5"
    const val atsl_runner = "1.0.1"
    const val hamcrest = "1.3"
    const val kotlin = "1.2.41"
    const val paging = "1.0.0"
    const val jodatime = "2.9.9.4"
    const val fotoapparat = "1.4.1"
    const val guava = "23.6-android"
    const val room: String = "2.0.0-alpha1"
    const val lifecycle: String = "2.0.0-alpha1"
    const val navigation = "1.0.0-alpha01"
}

object Deps {

    //android
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"

    //androidx
    const val androidx_core = "androidx.core:core-ktx:${Versions.androidx}"
    const val exifinterface = "androidx.exifinterface:exifinterface:${Versions.androidx}"
    const val annotations = "androidx.annotation:annotation :${Versions.androidx}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.androidx}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.androidx}"
    const val cardview = "\tandroidx.cardview:cardview:${Versions.androidx}"
    const val material = "com.google.android.material:material:${Versions.androidx}"
    const val constraint_layout_solver = "androidx.constraintlayout:constraintlayout-solver:${Versions.constraint_layout}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"

    const val navigation_fragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}" // use -ktx for Kotlin
    const val navigation_ui = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}" // use -ktx for Kotlin
    const val navigation_testing = "android.arch.navigation:navigation-testing-ktx:${Versions.navigation}" // use -ktx for Kotlin

    //room
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_rxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    const val room_testing = "androidx.room:room-testing:${Versions.room}"
    const val room_guava = "androidx.room:room-guava:${Versions.room}"

    //anko
    const val anko = "org.jetbrains.anko:anko:${Versions.anko}"
    const val anko_constraint = "org.jetbrains.anko:anko-constraint-layout:${Versions.anko}"
    const val anko_design = "org.jetbrains.anko:anko-design:${Versions.anko}"
    const val anko_appcompat_v7 = "org.jetbrains.anko:anko-appcompat-v7:${Versions.anko}"
    const val anko_recyclerview = "org.jetbrains.anko:anko-recyclerview-v7:${Versions.anko}"

    //lifecycle
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    // alternately - if using Java8, use the following instead of compiler
    const val lifecycle_java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    const val arch_core_testing = "androidx.arch.core:core-testing:${Versions.lifecycle}"

    //refrofit
    const val retrofit_runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit_mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val retrofit_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"

    //dagger2
    const val dagger_runtime = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger_android_support_compiler = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    //espresso
    const val espress_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espress_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val espress_intents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"

    //android test runner
    const val atsl_runner = "androidx.test:runner:${Versions.atsl_runner}"
    const val atsl_rules = "androidx.test:rules:${Versions.atsl_runner}"

    //mockito
    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockito_all = "org.mockito:mockito-all:${Versions.mockito_all}"

    //kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlin_test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    //glide
    const val glide_runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compile = "com.github.bumptech.glide:compiler:${Versions.glide}"

    //google
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val guava = "com.google.guava:guava:${Versions.guava}"

    //reactivex
    const val rxkotlin2 = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin2}"
    const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"

    const val jodatime = "net.danlew:android.joda:${Versions.jodatime}"
    const val fotoapparat = "io.fotoapparat.fotoapparat:library:${Versions.fotoapparat}"

    //for test
    const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
    const val junit = "junit:junit:${Versions.junit}"

}

object Configs {
    const val min_sdk = 26
    const val compile_sdk = "android-P"
    const val target_sdk = "27"
    const val build_tools = "28.0.0 rc2"
    const val version_code = 1
    const val version_name = "0.0.1"

}

