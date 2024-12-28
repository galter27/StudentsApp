package com.example.studentsapp.models

data class Student(
    val name: String,
    val id: String,
    val phoneNumber: Number,
    val address: String,
    var isChecked: Boolean,
)