package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentDatabase
import com.example.studentsapp.models.Student

class AddNewStudentActivity : AppCompatActivity() {

    private lateinit var idEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var addButton: Button
    private lateinit var cancelButton: Button
    private lateinit var checkedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student)

        // Initialize the views
        idEditText = findViewById(R.id.edit_text_id)
        nameEditText = findViewById(R.id.edit_text_name)
        phoneNumberEditText = findViewById(R.id.edit_text_phone_number)
        addressEditText = findViewById(R.id.edit_text_address)
        addButton = findViewById(R.id.button_add_student)
        cancelButton = findViewById(R.id.button_cancel)
        checkedCheckBox = findViewById(R.id.checkbox_checked)

        // Set up the Add Student button
        addButton.setOnClickListener {
            val id = idEditText.text.toString()
            val name = nameEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString().toLongOrNull()
            val address = addressEditText.text.toString()
            val isChecked = checkedCheckBox.isChecked  // Capture the state of the checkbox

            if (id.isNotEmpty() && name.isNotEmpty() && phoneNumber != null && address.isNotEmpty()) {
                // Create a new student with the checkbox state for isChecked
                val student = Student(
                    id = id,  // Allow the user to input the student ID
                    name = name,
                    phoneNumber = phoneNumber,
                    address = address,
                    isChecked = isChecked  // Set the isChecked value based on the checkbox
                )
                // Add the new student to the database
                StudentDatabase.students.add(student)

                // Optionally, return to the previous activity
                finish()
            }
        }

        // Set up the Cancel button to go back to the students list
        cancelButton.setOnClickListener {
            // Navigate to the Students List Activity
            val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
            startActivity(intent)
            finish()  // Close the AddStudentActivity so it doesn't stay in the back stack
        }
    }
}
