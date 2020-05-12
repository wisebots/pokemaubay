object Modules {
    val app = ":app"
    val core = ":commons:core"
    val cache = ":commons:cache"
    val network = ":commons:network"
    val presentation = ":commons:presentation"
}

object Features {
    val home = ":features:home"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0.0"
}

object Versions {
    val gradle = "3.5.2"

    val compileSdk = 29
    val minSdk = 24
    val targetSdk = 29

    val googleAuth = "16.0.1"
    val googleServices = "4.3.3"

    val firebaseAuth = "16.0.4"
    val firebaseCore = "17.2.2"
    val firebaseCrashlytics = "17.0.0-beta01"
    val firebaseAppDistribution = "1.1.0"

    val appcompat = "1.2.0-alpha01"
    val design = "1.2.0-alpha03"
    val supportLayout = "1.1.3"
    val cardview = "1.0.0"
    val recyclerview = "1.2.0-alpha01"
    val swiperefreshlayout = "1.1.0-alpha03"
    val coordinatorLayout = "1.1.0"

    val ktx = "1.0.0-alpha1"

    val navigation = "2.1.0-beta02"
    val paging = "2.1.0"

    val kotlin = "1.3.61"
    val rxjava = "2.2.16"
    val rxkotlin = "2.4.0"
    val retrofit = "2.7.0"
    val room = "2.1.0"
    val okhttp = "4.2.2"
    val espresso = "1.0.0"
    val glide = "4.11.0"
    val rxpaper = "1.4.0"
    val lifecycle = "2.1.0"
    val lifecycleCompiler = "2.1.0"
    val coroutines = "1.2.1"
    val koin = "2.1.0-alpha-8"
    val architecture = "2.2.0-rc03"

    val playCore = "1.6.4"

    val junit = "4.12"
    val assertjCore = "3.14.0"
    val mockitoKotlin = "2.1.0"
    val mockitoInline = "3.2.4"

    val ankoVersion = "0.10.8"
}

object Libraries {
    val rxpaper = "com.github.pakoito:RxPaper2:${Versions.rxpaper}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val anko = "org.jetbrains.anko:anko:${Versions.ankoVersion}"
    val ankoCommons = "org.jetbrains.anko:anko-design:${Versions.ankoVersion}"
    val ankoDesign = "org.jetbrains.anko:anko-commons:${Versions.ankoVersion}"

}

object NavigationLibraries {
    val navigationRuntime = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    val navigationUI = "androidx.navigation:navigation-ui:${Versions.navigation}"
    val navigationktxFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationktxUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val navigationPaging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object ReactiveLibraries {
    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"
}

object NetworkLibraries {
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    val espresso = "com.jakewharton.espresso:okhttp3-idling-resource:${Versions.espresso}"
}

object DatabaseLibraries {
    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

object KoinLibraries {
    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object KotlinLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
}

object ArchitectureLibraries {
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleCompiler}"
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.architecture}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.architecture}"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.supportLayout}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    val coordinatorlayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinatorLayout}"
}

object GoogleLibraries {
    val auth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    val playCore = "com.google.android.play:core:${Versions.playCore}"
}

object FirebaseLibraries {
    val auth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
    val core = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    val analytics = "com.google.firebase:firebase-analytics:${Versions.firebaseCore}"
    val crashlytics = "com.google.firebase:firebase-crashlytics:${Versions.firebaseCrashlytics}"
    val appdistribution = "com.google.firebase:firebase-appdistribution-gradle:${Versions.firebaseAppDistribution}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
}