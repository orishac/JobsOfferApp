package com.example.tryandroid

import BussinessLayer.JobRequest
import DataLayer.JobsDataHandler
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditJobActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_job)
        val db = JobsDataHandler(this)
        val jobs = db.allJobs;
        val myIntent = intent
        val textBoxTitle = findViewById<EditText>(R.id.editTextTitle)
        textBoxTitle.setText(myIntent.getStringExtra("title"))
        val textBoxContent = findViewById<EditText>(R.id.editTextContent)
        textBoxContent.setText(myIntent.getStringExtra("content"))
        val textBoxDate = findViewById<EditText>(R.id.editTextDate)
        textBoxDate.setText(myIntent.getStringExtra("date"))

        val addJobButton = findViewById<Button>(R.id.submitButton)
        addJobButton.setOnClickListener {
            val id = myIntent.getIntExtra("id", -1)
            val textBoxTitle = findViewById<EditText>(R.id.editTextTitle)
            val textBoxContent = findViewById<EditText>(R.id.editTextContent)
            val textBoxDate = findViewById<EditText>(R.id.editTextDate)
            val title = textBoxTitle.text.toString();
            val content = textBoxContent.text.toString();
            val date = textBoxDate.text.toString()
            val job = JobRequest(id, title, content, date);
            db.updateJob(job)
            val intent = Intent(this, EmployerActivity::class.java).apply {  }
            startActivity(intent)
            finish()
        }

        val deleteJobButton = findViewById<Button>(R.id.deleteButton)
        deleteJobButton.setOnClickListener {
            val id = myIntent.getIntExtra("id", -1)
            val job = db.getJob(id)
            db.deleteJob(job)
            val intent = Intent(this, EmployerActivity::class.java).apply {  }
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, EmployerActivity::class.java)
        startActivity(intent)
        finish()
    }
}