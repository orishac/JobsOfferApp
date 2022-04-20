package com.example.tryandroid

import BussinessLayer.JobRequest
import DataLayer.JobsDataHandler
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class AddJobActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_job)
        val db = JobsDataHandler(this)
        val addJobButton = findViewById<Button>(R.id.submitButton)
        addJobButton.setOnClickListener {
            val textBoxTitle = findViewById<EditText>(R.id.editTextTitle)
            val textBoxContent = findViewById<EditText>(R.id.editTextContent)
            val textBoxDate = findViewById<EditText>(R.id.editTextDate)
            val title = textBoxTitle.text.toString();
            val content = textBoxContent.text.toString();
            val date = textBoxDate.text.toString()
            val job = JobRequest(title, content, date);
            db.addJob(job);
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