package com.app.practice.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.practice.presentation.ui.theme.ComposePracticeTheme
import com.app.practice.domain.R
import com.app.practice.presentation.viewmodel.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        MainScreen()
    }
}

sealed class MainNavigationItem(val route: String, val icon: ImageVector, val name: String) {
    object Main: MainNavigationItem("Main", Icons.Filled.Home, "Main")
    object Category: MainNavigationItem("Category", Icons.Filled.Star, "Category")
    object MyPage: MainNavigationItem("MyPage", Icons.Filled.AccountBox, "MyPage")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewModel>()

    // Material 3에서는 Scaffold 대신, SnackbarHostState를 직접 사용함
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            Header(viewModel)
        },
        bottomBar = {
            MainBottomNavigationBar(navController)
        }
    ) { paddingValues -> // 패딩 값 무시
        MainNavigationScreen(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(viewModel: MainViewModel) {
    TopAppBar(
        title = { Text("TopAppBar") },
        actions = {
            IconButton(onClick = {
                viewModel.openSearchForm()
            } ) {
                Icon(Icons.Filled.Search, "SearchIcon")
            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
    val bottomNavigationItems = listOf(
        MainNavigationItem.Main,
        MainNavigationItem.Category,
        MainNavigationItem.MyPage
    )
    NavigationBar {
        val newBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = newBackStackEntry?.destination?.route

        bottomNavigationItems.forEach {item ->
            NavigationBarItem(
                selected = (currentRoute == item.route),
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(item.icon, contentDescription = item.route)
                }
            )

        }
    }
}

@Composable
fun MainNavigationScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = MainNavigationItem.Main.route) {
        composable(MainNavigationItem.Main.route) {
            Text("Hello Main")
        }
        composable(MainNavigationItem.Category.route) {
            Text("Hello Category")
        }
        composable(MainNavigationItem.MyPage.route) {
            Text("Hello MyPage")
        }
    }
}