package com.dzem.auth.data

import android.util.Patterns
import com.dzem.auth.domain.PatternValidator

object EmailPatternValidator: PatternValidator {
    override fun matches(value: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(value).matches()
}