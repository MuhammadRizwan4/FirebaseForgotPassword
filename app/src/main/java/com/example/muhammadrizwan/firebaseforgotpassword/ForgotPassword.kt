package com.example.muhammadrizwan.firebaseforgotpassword

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {

    var Email : String? = null
    var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val email = findViewById<TextInputLayout>(R.id.frEmail)
        val resetBtn = findViewById<Button>(R.id.ResetBtn)
        resetBtn.setOnClickListener {
          validation(email)
            sendEmail(Email)
        }


    }

    private fun sendEmail(email: String?) {
        auth.sendPasswordResetEmail(email!!).addOnCompleteListener({
            if (it.isSuccessful) {
                Toast.makeText(this,"Check your password reset email at $email",Toast.LENGTH_SHORT).show()
            }
        }).addOnFailureListener({
            Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
        })
    }

    private fun validation(email: TextInputLayout?) {
        if(!email!!.editText!!.text.isEmpty())
        {
            Email = email.editText!!.text.toString().trim()
            email.setError(null)
        }else{
            email.setError("Enter Email")
        }
    }
}
