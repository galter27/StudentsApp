package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentDatabase
import com.example.studentsapp.models.Student


class EditStudentActivity : AppCompatActivity() {

    private var student: Student? = null
    private var studentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Retrieve the student ID (you can pass this through Intent if necessary)
        studentId = intent.getStringExtra("student_id")

        // Fetch the student from the database
        student = StudentDatabase.students.find { it.id == studentId }

        student?.let { student ->
            // Initialize EditTexts with student details
            val nameEditText = findViewById<EditText>(R.id.edit_text_name)
            val phoneEditText = findViewById<EditText>(R.id.edit_text_phone)
            val addressEditText = findViewById<EditText>(R.id.edit_text_address)

            nameEditText.setText(student.name)
            phoneEditText.setText(student.phoneNumber.toString())
            addressEditText.setText(student.address)

            // Save Button to update the student details
            findViewById<Button>(R.id.button_save).setOnClickListener {
                // Save the updated student details
                student.name = nameEditText.text.toString()
                student.phoneNumber = phoneEditText.text.toString().toLong() as Number
                student.address = addressEditText.text.toString()

                // Update the student in the database
                StudentDatabase.students.find { it.id == student.id }?.apply {
                    name = student.name
                    phoneNumber = student.phoneNumber
                    address = student.address
                }

                // Optionally, return to the previous screen
                finish()
            }

            // Cancel Button
            findViewById<Button>(R.id.button_cancel).setOnClickListener {
                finish()
            }

            // Delete Button
            findViewById<Button>(R.id.button_delete).setOnClickListener {
                student.let { student ->
                    // Remove student from the database
                    StudentDatabase.students.remove(student)
                    val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
                    // Ensure the EditStudentActivity is closed
                    finish()
                }
            }

        }
    }
}
