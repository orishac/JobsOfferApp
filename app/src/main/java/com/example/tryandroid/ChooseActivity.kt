package com.example.tryandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        val employersButton = findViewById<Button>(R.id.employersButton)
        employersButton.setOnClickListener {
            val intent = Intent(this, EmployerActivity::class.java)
            startActivity(intent)
        }

        val seekerButton = findViewById<Button>(R.id.seekersButton)
        seekerButton.setOnClickListener {
            val intent = Intent(this, SeekerActivity::class.java)
            startActivity(intent)
        }

        val sp = getSharedPreferences("login", MODE_PRIVATE)

        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            sp.edit().putBoolean("logged", false).apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}

