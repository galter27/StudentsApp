package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentDatabase
import com.example.studentsapp.models.Student

class EditStudentActivity : AppCompatActivity() {

    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val nameEditText: EditText = findViewById(R.id.edit_student_name)
        val idEditText: EditText = findViewById(R.id.edit_student_id)
        val phoneEditText: EditText = findViewById(R.id.edit_student_phone)
        val addressEditText: EditText = findViewById(R.id.edit_student_address)
        val saveButton: Button = findViewById(R.id.save_student_button)

        // Retrieve the student ID from the intent
        val studentId = intent.getStringExtra("student_id")

        // Find the student in the database
        student = StudentDatabase.students.find { it.id == studentId }

        // Populate the EditText fields with the student's data
        student?.let {
            nameEditText.setText(it.name)
            idEditText.setText(it.id)
            phoneEditText.setText(it.phoneNumber.toString())
            addressEditText.setText(it.address)
        }

        // Save changes when the button is clicked
        saveButton.setOnClickListener {
            student?.let {
                it.name = nameEditText.text.toString()
                it.id = idEditText.text.toString()
                it.phoneNumber = phoneEditText.text.toString().toLongOrNull() ?: it.phoneNumber
                it.address = addressEditText.text.toString()

                // Now update the StudentDatabase with the edited student
                val index = StudentDatabase.students.indexOfFirst { it.id == it.id }
                if (index != -1) {
                    StudentDatabase.students[index] = it // Update the student in the database
                }

                // Notify the adapter to refresh the data in the RecyclerView
                val intent = Intent()
                setResult(RESULT_OK, intent)
                finish() // Close the activity and go back to the previous screen
            }
        }
    }
}
