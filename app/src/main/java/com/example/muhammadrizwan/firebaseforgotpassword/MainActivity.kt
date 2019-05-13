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

class MainActivity : AppCompatActivity() {

    var Email : String? = null
    var Password : String? = null
    var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val frTv = findViewById<TextView>(R.id.frTv)
        val crAccount = findViewById<Button>(R.id.crAccount)
        val email = findViewById<TextInputLayout>(R.id.email)
        val password = findViewById<TextInputLayout>(R.id.password)
        val loginBtn = findViewById<Button>(R.id.LoginBtn)
        frTv.setOnClickListener {
            startActivity(Intent(this,ForgotPassword::class.java))
        }
        crAccount.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
        }
        loginBtn.setOnClickListener {
            validation(email,password)
            loginUser(Email,Password)
        }

    }

    private fun loginUser(email: String?, password: String?) {
        try {
            auth.signInWithEmailAndPassword(email!!,password!!).addOnCompleteListener({
            if (it.isSuccessful) {
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
            }
        }).addOnFailureListener({
            Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
        })
    }catch (e:Exception)
        {
            e.printStackTrace()
        }
    }

    private fun validation(email: TextInputLayout?, password: TextInputLayout?) {
        if(!email!!.editText!!.text.isEmpty()){

            Email = email.editText!!.text.toString().trim()
           // email.isErrorEnabled = false
            email.setError(null)

        }else{
            email.setError("Enter Your Email")
        }
        if(!password!!.editText!!.text.isEmpty()){

            Password = password.editText!!.text.toString().trim()
            password.setError(null)
        }else{
            password.setError("Enter Password")
        }

    }
}
