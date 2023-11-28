package com.example.senseisurvey.ui.feature.auth.signIn

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.senseisurvey.R
import com.example.senseisurvey.ui.component.ErrorSnackbar
import com.example.senseisurvey.ui.component.SignInSignUpTopBar
import com.example.senseisurvey.ui.design.SignInSignUpScreen
import com.example.senseisurvey.ui.modifiers.supportWideScreen
import com.example.senseisurvey.ui.theme.SenseiSurveyTheme
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    email: String?,
    onSignInSubmitted: (email: String, password: String) -> Unit,
    onSignInAsGuest: () -> Unit,
    onNavUp: () -> Unit
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    val snackbarErrorText = stringResource(id = R.string.feature_not_available)
    val snackbarActionLabel = stringResource(id = R.string.dismiss)

    Scaffold(
        topBar = {
            SignInSignUpTopBar(
                topAppBarText = stringResource(id = R.string.sign_in),
                onNavUp = onNavUp
            )
        },
        content = { contentPadding ->
            SignInSignUpScreen(
                modifier = Modifier.supportWideScreen(),
                contentPadding = contentPadding,
                onSignInAsGuest = onSignInAsGuest
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    SignInContent(
                        email = email,
                        onSignInSubmitted = onSignInSubmitted
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = snackbarErrorText,
                                    actionLabel = snackbarActionLabel
                                )
                            }
                        },
                    ) {
                        Text(text = stringResource(id = R.string.forgot_password))
                    }
                }
            }
        }
    )
    Box(modifier = Modifier.fillMaxSize()) {
        ErrorSnackbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            snackbarHostState = snackbarHostState,
            onDismiss = { snackbarHostState.currentSnackbarData?.dismiss() },
        )
    }
}

@Composable
fun SignInContent(email: String?, onSignInSubmitted: (email: String, password: String) -> Unit) {
    TODO("Not yet implemented")
}

@Preview(name = "Sign in light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Sign in dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignInPreview() {
    SenseiSurveyTheme {
        SignInScreen(
            email = null,
            onSignInSubmitted = { _, _ -> },
            onSignInAsGuest = {},
            onNavUp = {},
        )
    }
}
