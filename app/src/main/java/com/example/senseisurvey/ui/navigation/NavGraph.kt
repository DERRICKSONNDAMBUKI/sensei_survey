package com.example.senseisurvey.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.senseisurvey.ui.feature.auth.signUp.SignUpRoute
import com.example.senseisurvey.ui.feature.survey.SurveyResultScreen
import com.example.senseisurvey.ui.feature.survey.SurveyRoute
import com.example.senseisurvey.ui.feature.welcome.WelcomeRoute
import com.example.senseisurvey.ui.navigation.Destination.SIGN_IN_ROUTE
import com.example.senseisurvey.ui.navigation.Destination.SIGN_UP_ROUTE
import com.example.senseisurvey.ui.navigation.Destination.SURVEY_RESULTS_ROUTE
import com.example.senseisurvey.ui.navigation.Destination.SURVEY_ROUTE
import com.example.senseisurvey.ui.navigation.Destination.WELCOME_ROUTE

object Destination {
    const val WELCOME_ROUTE = "welcome"
    const val SIGN_UP_ROUTE = "signup/{email}"
    const val SIGN_IN_ROUTE = "signin/{email}"
    const val SURVEY_ROUTE = "survey"
    const val SURVEY_RESULTS_ROUTE = "surveyresults"
}

@Composable
fun SenseiSurveyNavHost(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = WELCOME_ROUTE
    ) {
        composable(WELCOME_ROUTE) {
            WelcomeRoute(
                onNavigateToSignIn = {
                    navHostController.navigate("signin/$it")
                },
                onNavigateToSignUp = {
                    navHostController.navigate("signup/$it")
                },
                onSignInAsGuest = {
                    navHostController.navigate(SURVEY_ROUTE)
                }
            )
        }

        composable(SIGN_IN_ROUTE) {
            val startingEmail = it.arguments?.getString("email")
            SignUpRoute(
                email = startingEmail,
                onSignUpSubmitted = { navHostController.navigate(SURVEY_ROUTE) },
                onSignInAsGuest = { navHostController.navigate(SURVEY_ROUTE) },
                onNavUp = navHostController::navigateUp
            )
        }

        composable(SIGN_UP_ROUTE) {
            val startingEmail = it.arguments?.getString("email")
            SignUpRoute(
                email = startingEmail,
                onSignUpSubmitted = { navHostController.navigate(SURVEY_ROUTE) },
                onSignInAsGuest = { navHostController.navigate(SURVEY_ROUTE) },
                onNavUp = navHostController::navigateUp
            )
        }

        composable(SURVEY_ROUTE) {
            SurveyRoute(
                onSurfaceComplete = { navHostController.navigate(SURVEY_RESULTS_ROUTE) },
                onNavUp = navHostController::navigateUp
            )
        }
        composable(SURVEY_RESULTS_ROUTE) {
            SurveyResultScreen {
                navHostController.popBackStack(WELCOME_ROUTE, false)
            }
        }
    }
}