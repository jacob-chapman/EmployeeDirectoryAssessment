package com.jacobchapman.plugins

import org.gradle.api.Project
import javax.inject.Inject

open class AndroidModuleExtension @Inject constructor(private val project: Project) {
    fun configureModule(block: AndroidModuleConfiguration.() -> Unit) {
        val configuration = AndroidModuleConfiguration()
        block(configuration)
        configuration.libraries.forEach { library ->
            project.plugins.findPlugin(AndroidModulePlugin::class.java)
                ?.addDependency(project, library.title, library.configuration)
        }
        configuration.modules.forEach { module ->
            project.plugins.findPlugin(AndroidModulePlugin::class.java)
                ?.addDependencyProject(project, module.title, IMPLEMENTATION)
        }
    }
}

class AndroidModuleConfiguration {
    var isApp: Boolean = false
    var isFeatureModule: Boolean = false
    var libraries: MutableList<Libraries> = mutableListOf(Libraries.Hilt, Libraries.HiltCompiler)
    var modules: MutableList<Modules> = mutableListOf()
}

enum class Modules(val title: String) {
    Network(":network"),
    Domain(":domain"),
    Core(":core");
}

private const val IMPLEMENTATION = "implementation"
private const val DEBUG_IMPLEMENTATION = "debugImplementation"
private const val KAPT = "kapt"
private const val TEST_IMPLEMENTATION = "testImplementation"
private const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"

enum class Libraries(val title: String, val configuration: String) {
    ComposeUI("androidx.compose.ui:ui:${Versions.compose}", IMPLEMENTATION),
    ComposeNavigation(
        "androidx.navigation:navigation-compose:${Versions.navigation}",
        IMPLEMENTATION
    ),
    ComposeRuntime("androidx.compose.runtime:runtime:${Versions.compose}", IMPLEMENTATION),
    ComposeMaterial("androidx.compose.material:material:${Versions.compose}", IMPLEMENTATION),
    SwipeToRefresh(
        "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}",
        IMPLEMENTATION
    ),
    Coil("io.coil-kt:coil-compose:${Versions.coil}", IMPLEMENTATION),
    Material("androidx.compose.material3:material3:${Versions.material}", IMPLEMENTATION),
    CoreKtx("androidx.core:core-ktx:${Versions.coreKtx}", IMPLEMENTATION),
    PREVIEW("androidx.compose.ui:ui-tooling-preview:${Versions.compose}", IMPLEMENTATION),
    UiTooling("androidx.compose.ui:ui-tooling:${Versions.compose}", DEBUG_IMPLEMENTATION),
    TestManifest("androidx.compose.ui:ui-test-manifest:${Versions.compose}", DEBUG_IMPLEMENTATION),
    LifecycleRuntime(
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtimeKtx}",
        IMPLEMENTATION
    ),
    ActivityCompose(
        "androidx.activity:activity-compose:${Versions.activityCompose}",
        IMPLEMENTATION
    ),

    // hilt
    Hilt("com.google.dagger:hilt-android:${Versions.hilt}", IMPLEMENTATION),
    HiltNavigation("androidx.hilt:hilt-navigation-compose:${Versions.androidHilt}", IMPLEMENTATION),
    HiltCompiler("com.google.dagger:hilt-compiler:${Versions.hilt}", KAPT),

    // network
    Retrofit("com.squareup.retrofit2:retrofit:${Versions.retrofit}", IMPLEMENTATION),
    Moshi("com.squareup.moshi:moshi:${Versions.moshi}", IMPLEMENTATION),
    MoshiKotlin("com.squareup.moshi:moshi-kotlin:${Versions.moshi}", IMPLEMENTATION),
    MoshiConverter("com.squareup.retrofit2:converter-moshi:${Versions.retrofit}", IMPLEMENTATION),
    MockServer("com.squareup.okhttp3:mockwebserver:${Versions.okHttp}", "testImplementation"),

    // test
    Junit("junit:junit:${Versions.junit}", TEST_IMPLEMENTATION),
    ComposeJunit(
        "androidx.compose.ui:ui-test-junit4:${Versions.compose}",
        ANDROID_TEST_IMPLEMENTATION
    ),
    Turbine("app.cash.turbine:turbine:${Versions.turbine}", TEST_IMPLEMENTATION),
    CoroutinesTest(
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}",
        TEST_IMPLEMENTATION
    ),
    Mockito("org.mockito.kotlin:mockito-kotlin:${Versions.mockito}", TEST_IMPLEMENTATION);
}