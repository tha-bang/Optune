package com.example.optune.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.optune.ui.models.UserData
import kotlin.math.min


@Composable
fun DashBoard(
    navController: NavController,
) {
    val userData = UserData()
    ProfileDashboardScreen(
        navController = navController,
        userData = UserData(
            name = userData.name,
            email = userData.email,
            profileImage = userData.profileImage,
            joinDate = userData.joinDate,
            progress = calculateProgress(userData.skills),
            coursesTaken = userData.courses.size,
            skills = userData.skills,
            interests = userData.interests
        )
    )
}

private fun calculateProgress(skills: List<String>): Int {
    return min((skills.size * 10), 100)
}
