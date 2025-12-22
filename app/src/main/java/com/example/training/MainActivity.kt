package com.example.training

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.training.components.BottomNavigationBar
import com.example.training.navigation.NavigationItem.DashboardItem
import com.example.training.navigation.NavigationItem.ProfileItem
import com.example.training.navigation.NavigationItem.SettingsItem
import com.example.training.navigation.NavigationItem.WorkoutItem
import com.example.training.router.rememberMainRouter
import fitness.theme.TrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrainingTheme {
                val router = rememberMainRouter(startKey = DashboardItem)

                Scaffold(
                    bottomBar = { BottomNavigationBar(router = router) },
                    modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)
                ) { paddingValues ->
                    val modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)

                    NavDisplay(
                        modifier = modifier,
                        backStack = router.backStack,
                        onBack = { router.removeLast() },
                        entryProvider = entryProvider {
                            entry<DashboardItem> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("DashboardItem")
                                }
                            }
                            entry<WorkoutItem> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("WorkoutItem")
                                }
                            }
                            entry<ProfileItem> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("ProfileItem")
                                }
                            }
                            entry<SettingsItem> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("SettingsItem")
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
