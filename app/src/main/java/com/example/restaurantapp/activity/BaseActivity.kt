package com.example.restaurantapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivityBaseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        database = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)


    }

    private fun setUpButtonFunction(){

    }

    private fun setUpSignInButton(){

    }

    private fun setUpLoginButton(){
        binding
    }

}