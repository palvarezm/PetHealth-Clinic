package com.example.pethealth_clinic.Models

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val mail: String,
    val photo: String,
    val bio: String
)