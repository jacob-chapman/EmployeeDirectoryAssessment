package com.jacobchapman.employeedirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jacobchapman.employeedirectory.components.*
import com.jacobchapman.employeedirectory.ui.theme.EmployeeDirectoryTheme
import com.jacobchapman.employeedirectory.viewmodels.EmployeeDirectoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: EmployeeDirectoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeDirectoryTheme {
                val navController = rememberNavController()
                NavigationComponent(navHostController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EmployeeDirectoryTheme {

    }
}