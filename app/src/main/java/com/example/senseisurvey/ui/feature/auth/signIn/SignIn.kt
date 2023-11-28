package com.example.senseisurvey.ui.feature.auth.signIn

import androidx.compose.runtime.Composable

@Composable
fun SignInScreen(
    email:String?,
    onSignInSubmitted: (email:String,password:String)->Unit,
    onSignInGuest:()->Unit,
    onNavUp:()->Unit
){

}