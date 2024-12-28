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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
        // Redirect to EditStudentActivity with the student ID
        student?.let {
            val intent = Intent(this@StudentsRecyclerViewActivity, EditStudentActivity::class.java)
            intent.putExtra("student_id", it.id) // Pass the student ID to the next activity
            startActivity(intent)
        }
    }

    // Refresh the RecyclerView when returning from EditStudentActivity
    override fun onResume() {
        super.onResume()
        students = StudentDatabase.students // Refresh the students list
        findViewById<RecyclerView>(R.id.students_recycler_view).adapter?.notifyDataSetChanged() // Notify the adapter to refresh data
    }
}
