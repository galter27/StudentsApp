package com.example.studentsapp.data

import com.example.studentsapp.models.Student

object StudentDatabase {
    val students = mutableListOf(
        Student("John Doe", "1", 1234567890, "123 Main St", false),
        Student("Jane Smith", "2", 9876543210, "456 Oak Ave", false),
        Student("Michael Brown", "3", 1112223333, "789 Pine Rd", false),
        Student("Emily Davis", "4", 4445556666, "321 Maple Dr", false),
        Student("William Johnson", "5", 7778889999, "654 Elm St", false)
    )
}