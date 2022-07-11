package com.jacobchapman.plugins

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.*
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

fun Project.android(action: Action<BaseExtension>) {
    extensions.configure(BaseExtension::class.java, action)
}

class AndroidModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.create("androidmodule", AndroidModuleExtension::class.java, target)
        setupAndroid(target)
    }

    fun addDependency(project: Project, dependency: String, configuration: String) {
        project.dependencies {
            println("adding ${dependency}")
            add(configuration, dependency)
        }
    }

    fun addDependencyProject(project: Project, dependency: String, configuration: String) {
        project.dependencies {
            println("adding ${dependency}")
            add(configuration, project(dependency))
        }
    }

    private fun setupAndroid(project: Project) {
        project.android {
            when (this) {
                is AppExtension -> {}
                is LibraryExtension -> {
                    buildFeatures {
                        compose = true
                    }
                }
                is BaseAppModuleExtension -> {
                    buildFeatures {
                        compose = true
                    }
                }
                else -> {
                    println("Unsupported Android Variant")
                }
            }

            compileSdkVersion(AndroidConfig.compileSdkVersion)
            buildToolsVersion(AndroidConfig.buildToolsVersion)

            defaultConfig {
                if (this@android is BaseAppModuleExtension) {
                    applicationId = AndroidConfig.applicationId
                }
                minSdk = AndroidConfig.minSdkVersion
                targetSdk = AndroidConfig.targetSdkVersion
                versionCode = AndroidConfig.versionCode
                versionName = AndroidConfig.versionName

                testInstrumentationRunner = AndroidConfig.androidTestInstrumentation
                vectorDrawables {

                }
            }

            buildTypes {
                getByName("release") {
                    minifyEnabled(false)
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
            composeOptions {
                kotlinCompilerExtensionVersion = Versions.compose
            }
            packagingOptions {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
        }
    }
}

