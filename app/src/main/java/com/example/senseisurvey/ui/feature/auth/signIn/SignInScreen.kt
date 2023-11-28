package com.example.senseisurvey.ui.feature.auth.signIn

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.senseisurvey.R
import com.example.senseisurvey.ui.component.Email
import com.example.senseisurvey.ui.component.EmailState
import com.example.senseisurvey.ui.component.EmailStateSaver
import com.example.senseisurvey.ui.component.ErrorSnackbar
import com.example.senseisurvey.ui.component.Password
import com.example.senseisurvey.ui.component.PasswordState
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
    Column(modifier = Modifier.fillMaxWidth()) {
        val focusRequester = remember {
            FocusRequester()
        }
        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState(email = email))
        }
        Email(emailState, onImeAction = { focusRequester.requestFocus() })
        Spacer(modifier = Modifier.height(16.dp))
        val passwordState = remember {
            PasswordState()
        }
        val onSubmit = {
            if (emailState.isValid && passwordState.isValid) {
                onSignInSubmitted(emailState.text, passwordState.text)
            }
        }
        Password(
            modifier = Modifier.focusRequester(focusRequester = focusRequester),
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            onImeAction = { onSubmit }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            onClick = { onSubmit })
        {
            Text(text = stringResource(id = R.string.sign_in))
        }

    }
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
