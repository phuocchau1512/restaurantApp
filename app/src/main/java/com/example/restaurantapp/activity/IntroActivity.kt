package com.example.restaurantapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantapp.databinding.IntroMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: IntroMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IntroMainBinding.inflate(layoutInflater)
        database = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        setUpButtonFunction()
    }

    private fun setUpButtonFunction(){
        setUpLoginButton()
        setUpSignInButton()
    }

    private fun setUpSignInButton(){
        binding.signInTextView.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)

        }
    }

    private fun setUpLoginButton(){
        binding.loginTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}