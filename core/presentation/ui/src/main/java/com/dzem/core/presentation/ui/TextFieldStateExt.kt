package com.dzem.core.presentation.ui

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.Flow

val TextFieldState.textAsFlow: Flow<String>
    get() = snapshotFlow { text.toString() }