package com.jacobchapman.employeedirectory.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComponent(navHostController: NavHostController) {
    NavHost(navHostController, Routes.EMPLOYEE_DIRECTORY.path) {
        composable(Routes.EMPLOYEE_DIRECTORY.path) {
            EmployeeDirectoryScreen(viewModel = hiltViewModel())
        }
    }
}

enum class Routes(val path: String) {
    EMPLOYEE_DIRECTORY("employee_directory");
}