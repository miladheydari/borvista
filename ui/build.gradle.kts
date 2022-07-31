import dependencies.UiDep

plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.navigationSafArgs)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.dagger)
}

android {
    compileSdkVersion(Config.Android.androidCompileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.Android.androidMinSdkVersion)
        targetSdkVersion(Config.Android.androidTargetSdkVersion)
        versionCode(Environments.Release.appVersionCode)
        versionName(Environments.Release.appVersionName)

        testInstrumentationRunner(Config.testRunner)

        // Configs
        buildConfigField("String", "BASE_URL", "\"" + Environments.Release.baseUrl + "\"")
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    flavorDimensions("type")

    productFlavors {
        create("Simpsons") {
            dimension("type")
            applicationId = "com.sample.simpsonsviewer"

            buildConfigField(
                "String",
                "QUERY",
                "\"simpsons+characters\""
            )
        }
        create("Wire") {
            dimension("type")
            applicationId = "com.sample.wireviewer"

            buildConfigField(
                "String",
                "QUERY",
                "\"the+wire+characters\""
            )


        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Modules
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.remote))
    implementation(project(Modules.presentation))

    // Core Dependencies
    implementation(UiDep.kotlin)
    implementation(UiDep.coreKtx)
    implementation(UiDep.appCompat)
    implementation(UiDep.material)
    implementation(UiDep.constraint)
    implementation(UiDep.navigationFragmentKtx)
    implementation(UiDep.navigationUiKtx)
    implementation(UiDep.activityKtx)
    // LifeCycle
    UiDep.LifeCycle.forEach {
        implementation(it)
    }
    // Dagger-Hilt
    UiDep.DaggerHilt.forEach {
        implementation(it)
    }
    UiDep.DaggerHiltKapt.forEach {
        kapt(it)
    }
    // Coroutines
    UiDep.Coroutines.forEach {
        implementation(it)
    }
    // Glide
    implementation(UiDep.glide)
    kapt(UiDep.glideKapt)
    // Timber
    implementation(UiDep.timber)
    // Lottie animation
    implementation(UiDep.lottie)

    }
