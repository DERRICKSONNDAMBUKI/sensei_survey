package com.example.senseisurvey.ui.feature.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.senseisurvey.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class WelcomeViewModel(private val userRepository: UserRepository) :
    ViewModel() {
    fun handleContinue(
        email: String,
        onNavigateToSignIn: (email: String) -> Unit,
        onNavigateToSignUp: (email: String) -> Unit
    ) {
        if (userRepository.isKnownUserEmail(email = email)) {
            onNavigateToSignIn(email)
        } else {
            onNavigateToSignUp(email)
        }
    }

    fun signInAsGuest(onSignInComplete: () -> Unit) {
        userRepository.signInAsGuest()
        onSignInComplete()
    }
}

class WelcomeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}