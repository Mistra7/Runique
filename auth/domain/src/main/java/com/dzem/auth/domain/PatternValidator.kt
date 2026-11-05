package com.dzem.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}