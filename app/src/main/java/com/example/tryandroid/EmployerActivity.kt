package com.example.tryandroid

import DataLayer.JobsDataHandler
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class EmployerActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employers)
        val jobsListView = findViewById<ListView>(R.id.jobsList)
        val db = JobsDataHandler(this)
        val jobs = db.allJobsTitles
        val adapter = ArrayAdapter(this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            jobs)
        jobsListView.adapter = adapter

        val addJobButton = findViewById<Button>(R.id.addJobButton)
        addJobButton.setOnClickListener {
            val intent = Intent(this, AddJobActivity::class.java).apply {
            }
            startActivity(intent)
            finish()
        }
        jobsListView.setOnItemClickListener { adapter, jobsListView, position, id ->
            val element = adapter.getItemIdAtPosition(position) // The item that was clicked
            val job = db.allJobs[element.toInt()]
            val intent = Intent(this, EditJobActivity::class.java).apply {
            }
            intent.putExtra("position", position)
            intent.putExtra("id", job.id)
            intent.putExtra("title", job.title)
            intent.putExtra("content", job.content)
            intent.putExtra("date", job.date)
            startActivity(intent)
            finish()
        }

    }
    override fun onResume() {
        val db = JobsDataHandler(this)
        super.onResume()
        val adapter = ArrayAdapter(this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            db.allJobsTitles)
        adapter.notifyDataSetChanged()
    }


}