package com.example.studentsapp.data

import com.example.studentsapp.models.Student


object StudentDatabase {
    // Ensure that this is a MutableList
    val students: MutableList<Student> = mutableListOf(
        // Add some sample students to test
        Student("Alice", "1", 1234567890, "123 Main St", false),
        Student("Bob", "2", 9876543210, "456 Elm St", false)
    )
}