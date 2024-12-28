package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.adapters.StudentsRecyclerAdapter
import com.example.studentsapp.models.Student
import com.example.studentsapp.data.StudentDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView


interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}

class StudentsRecyclerViewActivity : AppCompatActivity(), OnItemClickListener {

    private var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_recycler_view)

        // Set the title TextView
        val titleTextView: TextView = findViewById(R.id.sub_title)
        titleTextView.text = "Click on + to add new student"

        // Set up FAB click listener
        findViewById<FloatingActionButton>(R.id.fab_add_student).setOnClickListener {
            val intent = Intent(this, AddNewStudentActivity::class.java)
            startActivity(intent)
        }

        students = StudentDatabase.students
        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = StudentsRecyclerAdapter(students)

        // Assigning the listener directly in the adapter
        adapter.listener = this

        recyclerView.adapter = adapter
    }

    // Now implement the onItemClick methods
    override fun onItemClick(position: Int) {
        Log.d("TAG", "On click Activity listener on position $position")
    }

    override fun onItemClick(student: Student?) {
        student?.let {
            val intent = Intent(this@StudentsRecyclerViewActivity, StudentDetailsActivity::class.java)
            intent.putExtra("student_id", it.id) // Pass the student ID (make sure it's the correct type)
            startActivity(intent)
        }
    }

    // Refresh the RecyclerView when returning from EditStudentActivity
    override fun onResume() {
        super.onResume()
        students = StudentDatabase.students // Refresh the students list
        // Notify the adapter to refresh data
        findViewById<RecyclerView>(R.id.students_recycler_view).adapter?.notifyDataSetChanged()
    }
}
