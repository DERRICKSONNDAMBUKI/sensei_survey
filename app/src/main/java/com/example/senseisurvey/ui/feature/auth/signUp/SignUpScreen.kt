package com.example.senseisurvey.ui.feature.auth.signUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.senseisurvey.R
import com.example.senseisurvey.ui.component.ConfirmPasswordState
import com.example.senseisurvey.ui.component.Email
import com.example.senseisurvey.ui.component.EmailState
import com.example.senseisurvey.ui.component.Password
import com.example.senseisurvey.ui.component.PasswordState
import com.example.senseisurvey.ui.component.SignInSignUpTopBar
import com.example.senseisurvey.ui.design.SignInSignUpScreen
import com.example.senseisurvey.ui.modifiers.supportWideScreen
import com.example.senseisurvey.ui.theme.SenseiSurveyTheme
import com.example.senseisurvey.ui.theme.stronglyDeemphasizedAlpha

@Composable
fun SignUpScreen(
    email: String?,
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
fun SignUpContent(email: String?, onSignUpSubmitted: (email: String, password: String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val passwordFocusRequest = remember {
            FocusRequester()
        }
        val confirmationPasswordFocusRequester = remember {
            FocusRequester()
        }
        val emailState = remember {
            EmailState(email = email)
        }
        Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() })
        Spacer(modifier = Modifier.height(16.dp))
        val passwordState = remember {
            PasswordState()
        }
        Password(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            imeAction = ImeAction.Next,
            onImeAction = { confirmationPasswordFocusRequester.requestFocus() },
        )
        Spacer(modifier = Modifier.height(16.dp))
        val confirmationPasswordState = remember {
            ConfirmPasswordState(passwordState = passwordState)
        }
        Password(
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequester),
            label = stringResource(id = R.string.confirm_password),
            passwordState = confirmationPasswordState,
            onImeAction = {
                onSignUpSubmitted(emailState.text, passwordState.text)
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.terms_and_conditions),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(
                alpha = stronglyDeemphasizedAlpha
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSignUpSubmitted(emailState.text, passwordState.text) },
            enabled = emailState.isValid && passwordState.isValid && confirmationPasswordState.isValid
        ) {
            Text(text = stringResource(id = R.string.create_account))
        }
    }
}

@Preview(widthDp = 1024)
@Composable
fun SignUpPreview() {
    SenseiSurveyTheme {
        SignUpScreen(
            email = null,
            onSignUpSubmitted = { _, _ -> },
            onSignInAsGuest = { /*TODO*/ },
            onNavUp = {}
        )
    }
}