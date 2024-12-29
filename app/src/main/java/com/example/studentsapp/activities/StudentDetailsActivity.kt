package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        // Set up the Edit Button
        findViewById<Button>(R.id.button_edit).setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_id", studentId) // Pass the student ID
            startActivity(intent)
        }

        // Fetch and display student data
        displayStudentDetails()

        // Set up the Back Button
        findViewById<Button>(R.id.button_back).setOnClickListener {
            finish() // This will return to the previous activity
        }
    }
    
    override fun onResume() {
        super.onResume()

        // Refresh the student details when returning to this activity
        displayStudentDetails()
    }

    private fun displayStudentDetails() {
        // Fetch the student from the database using the ID
        student = StudentDatabase.students.find { it.id == studentId }

        // If the student is found, display the updated details
        student?.let {
            findViewById<TextView>(R.id.text_view_id).text = "ID: ${it.id}"
            findViewById<TextView>(R.id.text_view_name).text = "Name: ${it.name}"
            findViewById<TextView>(R.id.text_view_phone).text = "Phone: ${it.phoneNumber}"
            findViewById<TextView>(R.id.text_view_address).text = "Address: ${it.address}"

            // Display the static avatar image
            val avatarImageView = findViewById<ImageView>(R.id.image_view_avatar)
            avatarImageView.setImageResource(R.drawable.avatar)

            // Set the Checked status as TextView
            val checkedTextView = findViewById<TextView>(R.id.text_view_checked)
            checkedTextView.text = "Checked: ${if (it.isChecked) "Checked" else "Not Checked"}"
        }
    }
}
