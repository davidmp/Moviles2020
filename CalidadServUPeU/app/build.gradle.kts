
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    //id("dagger.hilt.android.plugin")
    //id("org.jlleitschuh.gradle.ktlint")
}

android {
    compileSdkVersion (30)
    buildToolsVersion ("30.0.2")

    defaultConfig {
        applicationId="pe.edu.upeu.calidadservupeu"
        minSdkVersion (28)
        targetSdkVersion (30)
        versionCode =1
        versionName ="1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures.viewBinding = true
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    compileOptions {
        sourceCompatibility=JavaVersion.VERSION_1_8
        targetCompatibility=JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))


    // Kotlin
    implementation(Dependencies.kotlin)
// Coroutines
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    // Android
    implementation(Android.appcompat)
    implementation(Android.activityKtx)
    implementation(Android.coreKtx)
    implementation(Android.constraintLayout)
    implementation(Android.swipeRefreshLayout)
    implementation(Android.legacyKts)
    implementation(Android.navegationFragment)
    implementation(Android.navegationUI)
    // Navegation
    implementation(Navigation.navegationFragmentKtx)
    implementation(Navigation.navegationUiKtx)
    // Architecture Components
    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.liveData)
    implementation(Lifecycle.lifeCicleExtension)

    // Room components
    implementation(Room.runtime)
    implementation(Room.ktx)
    kapt(Room.compiler)

    // Material Design
    implementation(Dependencies.materialDesign)
    implementation(Dependencies.materialDialog)

    // Coil-kt
    implementation(Dependencies.coil)

    // Retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.moshiRetrofitConverter)

    // Moshi
    implementation(Moshi.moshi)
    implementation(Moshi.codeGen)
    kapt(Moshi.codeGen)

    // Hilt + Dagger
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltViewModel)
    kapt(Hilt.daggerCompiler)
    kapt(Hilt.hiltCompiler)

    // Testing
    testImplementation(Testing.core)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.room)
    testImplementation(Testing.okHttp)
    testImplementation(Testing.jUnit)

    // Android Testing
    androidTestImplementation(Testing.extJUnit)
    androidTestImplementation(Testing.espresso)
    
    //Mapbox
    implementation(mapbox.mapbox)
    implementation(mapbox.location)

    //QR
    implementation(qr.zbar)

}