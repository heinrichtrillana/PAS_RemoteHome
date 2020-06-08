package com.example.remotehome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var username : EditText
    private lateinit var password : EditText

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)

        auth = FirebaseAuth.getInstance()
    }


    fun login(view : View){

        val user : String = username.text.toString()
        val pass : String = password.text.toString()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)){
            auth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener (this){
                    task ->
                    if(task.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "Error en la autenticación", Toast.LENGTH_SHORT)
                    }
                }
        }
    }
}
