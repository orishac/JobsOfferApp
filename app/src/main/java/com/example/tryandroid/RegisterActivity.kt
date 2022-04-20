package com.example.tryandroid

import BussinessLayer.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.w3c.dom.UserDataHandler

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val db = DataLayer.UserDataHandler(this)
        val singup = findViewById<Button>(R.id.signUpButton)
        singup.setOnClickListener {
            val textBoxUsername = findViewById<EditText>(R.id.usernameRegister)
            val textBoxEmail = findViewById<EditText>(R.id.emailRegister)
            val textBoxPassword = findViewById<EditText>(R.id.passwordRegister)
            val textBoxConfirmPassword = findViewById<EditText>(R.id.confirmPasswordRegister)
            val username = textBoxUsername.text.toString();
            val email = textBoxEmail.text.toString();
            val password = textBoxPassword.text.toString();
            val confirmPassword = textBoxConfirmPassword.text.toString();
            if (!isValidEmail(email)) {
                Toast.makeText(this, "Email address is not valid", Toast.LENGTH_SHORT).show()
            }
            else if (password!=confirmPassword) {
                Toast.makeText(this, "passwords does not match", Toast.LENGTH_SHORT).show()
            }
            else {
                val user = User(username, email, password, 0, 0)
                db.addUser(user)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun isValidEmail(email : String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            false;
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
}