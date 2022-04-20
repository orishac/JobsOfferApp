package com.example.tryandroid

import DataLayer.JobsDataHandler
import DataLayer.UserDataHandler
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeTheme = findViewById<Button>(R.id.btn_change_theme)
        checkTheme()
        changeTheme.setOnClickListener { chooseThemeDialog() }

        val db = UserDataHandler(this)
        val registerButtom = findViewById<Button>(R.id.registerButton)
        registerButtom.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        val sp = getSharedPreferences("login", MODE_PRIVATE)
        if (sp.getBoolean("logged", false)) {
            val intent = Intent(this, ChooseActivity::class.java)
            startActivity(intent)
            finish()
        }
        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val textBoxUsername = findViewById<EditText>(R.id.usernameEditText)
            val textBoxPassword = findViewById<EditText>(R.id.passwordEditText)
            val username = textBoxUsername.text.toString();
            val password = textBoxPassword.text.toString();
            try {
                val user = db.getUser(username)
                val passwordValidity = user.checkPasswordValidity(password)
                if (passwordValidity) {
                    sp.edit().putBoolean("logged", true).apply()
                    val intent = Intent(this, ChooseActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(this, "Password does not match this username, Try Again", Toast.LENGTH_SHORT).show()
                }
            }
            catch (exception : Exception) {
                Toast.makeText(this, "User Does Not Exist", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun chooseThemeDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose Theme")
        val styles = arrayOf("Light", "Dark", "System default")
        val checkedItem = MyPreferences(this).darkMode

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->

            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    MyPreferences(this).darkMode = 0
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    MyPreferences(this).darkMode = 1
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    MyPreferences(this).darkMode = 2
                    delegate.applyDayNight()
                    dialog.dismiss()
                }

            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun checkTheme() {
        when (MyPreferences(this).darkMode) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
        }
    }
}