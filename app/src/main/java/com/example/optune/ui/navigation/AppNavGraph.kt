package com.example.optune.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.optune.education.EducationPage
import com.example.optune.education.HighSchoolAndTertiaryPageForm
import com.example.optune.education.HighSchoolPageForm
import com.example.optune.education.NoEducationPageForm
import com.example.optune.education.TertiaryPageForm
import com.example.optune.ui.screens.DashboardScreen
import com.example.optune.ui.screens.SignInScreen
import com.example.optune.ui.screens.SignUpFormScreen
import com.example.optune.ui.screens.SkillsAndInterestsScreen


@Composable
fun AppNavGraph(
    showToast: (String) -> Unit
) {
    val navController = rememberNavController()

    val isSignedIn = false

    NavHost(
        navController = navController,
        startDestination = if (isSignedIn) "dashboard" else "signIn"
    ) {
        composable("signIn") {
            SignInScreen(navController = navController)
        }
        composable("signUp") {
            SignUpFormScreen(navController = navController)
        }
        composable("dashboard") {
            DashboardScreen(navController = navController)
        }
        composable("skillsAndInterests"){
            SkillsAndInterestsScreen(navController = navController)
        }
        composable("education"){
            EducationPage(navController = navController)
        }
        composable("highSchool"){
            HighSchoolPageForm(navController = navController)
        }
        composable("tertiary"){
            TertiaryPageForm(navController = navController)
        }
        composable("noEducation"){
            NoEducationPageForm(navController = navController)
        }
        composable("highSchoolAndTertiary") {
            HighSchoolAndTertiaryPageForm(navController = navController)
        }
    }
}
