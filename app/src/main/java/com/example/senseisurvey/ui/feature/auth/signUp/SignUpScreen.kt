package com.example.senseisurvey.ui.feature.auth.signUp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.glance.layout.Column
import com.example.senseisurvey.R
import com.example.senseisurvey.ui.component.SignInSignUpScreen
import com.example.senseisurvey.ui.component.SignInSignUpTopBar
import com.example.senseisurvey.ui.modifiers.supportWideScreen

@Composable
fun SignUpScreen(
    email: String,
    onSignUpSubmitted: (email: String, password: String) -> Unit,
    onSignInAsGuest: () -> Unit,
    onNavUp: () -> Unit
) {
    Scaffold(
        topBar = {
            SignInSignUpTopBar(
                topAppBarText = stringResource(id = R.string.create_account),
                onNavUp = onNavUp
            )
        },
        content = { contentPadding ->
            SignInSignUpScreen(
                modifier = Modifier.supportWideScreen(),
                onSignInAsGuest = onSignInAsGuest,
                contentPadding = contentPadding
            ) {
                Column {
                    SignUpContent(
                        email = email,
                        onSignUpSubmitted = onSignUpSubmitted
                    )
                }
            }
        }
    )
}

@Composable
fun SignUpContent(email: String, onSignUpSubmitted: (email: String, password: String) -> Unit) {
    TODO("Not yet implemented")
}