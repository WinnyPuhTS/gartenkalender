package com.gartenkalender.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gartenkalender.app.ui.screens.calendar.CalendarScreen
import com.gartenkalender.app.ui.screens.plants.PlantListScreen
import com.gartenkalender.app.ui.screens.todos.TodoScreen

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Calendar : Screen("calendar", "Kalender", Icons.Default.CalendarMonth)
    object Plants : Screen("plants", "Pflanzen", Icons.Default.Eco)
    object Todos : Screen("todos", "Aufgaben", Icons.Default.CheckCircle)
}

@Composable
fun GartenkalenderNavHost() {
    val navController = rememberNavController()
    val items = listOf(Screen.Calendar, Screen.Plants, Screen.Todos)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Calendar.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Calendar.route) { CalendarScreen() }
            composable(Screen.Plants.route) { PlantListScreen() }
            composable(Screen.Todos.route) { TodoScreen() }
        }
    }
}
