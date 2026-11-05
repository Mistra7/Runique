package com.dzem.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzem.auth.domain.AuthRepository
import com.dzem.auth.domain.UserDataValidator
import com.dzem.auth.presentation.R
import com.dzem.core.domain.util.DataError
import com.dzem.core.domain.util.Result
import com.dzem.core.presentation.ui.UiText
import com.dzem.core.presentation.ui.asUiText
import com.dzem.core.presentation.ui.textAsFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
): ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

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
        when(action) {
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }
            else -> Unit
        }
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = repository.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isRegistering = false)

            when(result) {
                is Result.Error -> {
                    if(result.error == DataError.Network.CONFLICT) {
                        eventChannel.send(RegisterEvent.Error(
                            UiText.StringResource(R.string.error_email_exists)
                        ))
                    } else {
                        eventChannel.send(RegisterEvent.Error(result.error.asUiText()))
                    }
                }
                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}