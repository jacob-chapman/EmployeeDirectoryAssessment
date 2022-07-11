import com.jacobchapman.plugins.Libraries
import com.jacobchapman.plugins.Modules

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("android-module")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}
repositories {
    mavenCentral()
    google()
}

android {
    buildFeatures {
        compose = true
    }
}

androidmodule {
    configureModule {
        libraries += mutableListOf(
            Libraries.ComposeUI,
            Libraries.ComposeNavigation,
            Libraries.ComposeRuntime,
            Libraries.ComposeMaterial,
            Libraries.Coil,
            Libraries.SwipeToRefresh,
            Libraries.HiltNavigation,
            Libraries.Material,
            Libraries.Junit,
            Libraries.ComposeJunit,
            Libraries.CoreKtx,
            Libraries.PREVIEW,
            Libraries.LifecycleRuntime,
            Libraries.ActivityCompose,
            Libraries.UiTooling,
            Libraries.TestManifest
        )
        modules += mutableListOf(Modules.Domain, Modules.Network, Modules.Core)
    }
}