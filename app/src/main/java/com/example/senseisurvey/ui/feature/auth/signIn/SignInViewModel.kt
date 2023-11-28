package com.example.senseisurvey.ui.feature.auth.signIn

import androidx.lifecycle.ViewModel
import com.example.senseisurvey.data.repository.UserRepository

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {
    /**
     * Consider all sign ins successful
     */
    fun signIn(
        email: String,
        password: String,
        onSignInComplete: () -> Unit
    ) {
        userRepository.signIn(email, password)
        onSignInComplete()
    }

}