package com.example.lojasocialfirebase.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MainScaffold(
    navController: NavController,
    userEmail: String,
    onLogout: () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = { TopNavBar(userEmail) },
        bottomBar = { BottomNavBar(navController, onLogout) }
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}
