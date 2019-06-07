package com.example.pethealth_clinic.Models

data class Appointment (
    val id: Int,
    val vetName: String,
    val veterinaryName: String,
    val petName : String,
    val date: String,
    val description: String,
    val prescription : String
)
