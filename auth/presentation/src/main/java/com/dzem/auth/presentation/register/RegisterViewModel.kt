package com.dzem.auth.presentation.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzem.auth.domain.UserDataValidator
import com.dzem.core.presentation.ui.textAsFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userDataValidator: UserDataValidator
): ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    init {
        viewModelScope.launch {
            state.email.textAsFlow
                .collectLatest { email ->
                    val isValidEmail = userDataValidator.isValidEmail(email.toString())
                    state = state.copy(
                        isEmailValid = isValidEmail,
                        canRegister = isValidEmail && state.passwordValidationState.isValidPassword && !state.isRegistering
                    )
                }
        }

        viewModelScope.launch {
            state.password.textAsFlow
                .collectLatest { password ->
                    val passwordValidationState = userDataValidator.validatePassword(password.toString())
                    state = state.copy(
                        passwordValidationState = passwordValidationState,
                        canRegister = state.isEmailValid && passwordValidationState.isValidPassword && !state.isRegistering
                    )
                }
        }
    }

    fun onAction(action: RegisterAction) {

    }
}