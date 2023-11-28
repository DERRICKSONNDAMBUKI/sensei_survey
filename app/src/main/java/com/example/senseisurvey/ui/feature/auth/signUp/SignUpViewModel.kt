package com.example.senseisurvey.ui.feature.auth.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.senseisurvey.data.repository.UserRepository

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    /**
     * Consider all sign ups successful
     */

    fun signUp(
        email: String,
        password: String,
        onSignUpComplete: () -> Unit
    ) {
        userRepository.signUp(email, password)
        onSignUpComplete()
    }

    fun signInAsGuest(onSignUpComplete: () -> Unit) {
        userRepository.signInAsGuest()
        onSignUpComplete()
    }
}

class SignUpViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}