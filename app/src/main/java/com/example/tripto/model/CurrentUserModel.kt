package com.example.tripto.model

data class CurrentUserModel (
        val email: String,
        val age: Int,
        val country: String,
        val username: String,
        val role_id: Int,
        val id: Int,
        val isActive: Boolean

    )