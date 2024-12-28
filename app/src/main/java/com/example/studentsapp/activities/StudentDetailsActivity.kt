package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentDatabase
import com.example.studentsapp.models.Student

class StudentDetailsActivity : AppCompatActivity() {

    private var studentId: String? = null
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        // Get the student ID from the Intent
        studentId = intent.getStringExtra("student_id")

        // Fetch the student from the database using the ID
        student = StudentDatabase.students.find { it.id == studentId }

        // If the student is found, display the details
        student?.let {
            // Display student details in TextViews
            findViewById<TextView>(R.id.text_view_name).text = "Name: ${it.name}"
            findViewById<TextView>(R.id.text_view_phone).text = "Phone: ${it.phoneNumber}"
            findViewById<TextView>(R.id.text_view_address).text = "Address: ${it.address}"

            val avatarImageView = findViewById<ImageView>(R.id.image_view_avatar)
            avatarImageView.setImageResource(R.drawable.avatar) // Set static image

            // Set the CheckBox state based on the student's checked status
            val checkedCheckBox = findViewById<CheckBox>(R.id.checkbox_checked)
            checkedCheckBox.isChecked = it.isChecked
        }

        // Set up the Back Button
        findViewById<Button>(R.id.button_back).setOnClickListener {
            finish()
        }
    }
}
