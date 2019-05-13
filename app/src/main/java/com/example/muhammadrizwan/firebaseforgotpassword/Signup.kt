package com.example.muhammadrizwan.firebaseforgotpassword

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class Signup : AppCompatActivity() {


    var upEmail : String? = null
    var upPassword : String? = null
    var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val Email = findViewById<TextInputLayout>(R.id.Email)
        var Password = findViewById<TextInputLayout>(R.id.Password)
        val Signup = findViewById<Button>(R.id.SignUpBtn)

        Signup.setOnClickListener {
            Validaton(Email,Password)
            signUp(upEmail,upPassword)


        }

    }

    private fun signUp(upEmail: String?, upPassword: String?) {
        try {
            auth.createUserWithEmailAndPassword(upEmail!!,upPassword!!).addOnCompleteListener({
                if (it.isSuccessful) {
                    Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                }
            }).addOnFailureListener({
                Toast.makeText(this, it.message.toString(),Toast.LENGTH_SHORT).show()
            })
    }catch (e:Exception)
        {
            e.printStackTrace()
        }
    }

    private fun Validaton(email: TextInputLayout?, password: TextInputLayout?) {
        if(!email!!.editText!!.text.isEmpty())
        {
            upEmail = email.editText!!.text.toString().trim()
            email.setError(null)
        }else{
            email.setError("Enter Email")
        }

        if(!password!!.editText!!.text.isEmpty())
        {
            upPassword = password.editText!!.text.toString().trim()
            password.setError(null)
        }else{
                password.setError("Enter Password")
        }

    }
}