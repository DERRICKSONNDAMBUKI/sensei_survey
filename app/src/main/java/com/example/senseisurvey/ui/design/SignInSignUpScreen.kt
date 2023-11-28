package com.example.senseisurvey.ui.design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.senseisurvey.ui.feature.welcome.WelcomeAsGuest
import com.example.senseisurvey.ui.theme.SenseiSurveyTheme

@Composable
fun SignInSignUpScreen(
    modifier: Modifier = Modifier,
    onSignInAsGuest: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        item {
            Spacer(modifier = Modifier.height(44.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                content()
            }
            Spacer(modifier = Modifier.height(16.dp))
            WelcomeAsGuest(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onSignInASGuest = onSignInAsGuest,
            )
        }
    }
}


@Preview
@Composable
fun SignInSignUpScreenPreview() {
    SenseiSurveyTheme {
        Surface {
            SignInSignUpScreen(
                onSignInAsGuest = {},
                content = {}
            )
        }
    }
}