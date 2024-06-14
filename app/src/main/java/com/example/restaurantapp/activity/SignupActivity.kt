package com.example.restaurantapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivitySignupBinding
import com.example.restaurantapp.helper.ValidationUtils
import com.example.restaurantapp.model.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var database: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        database = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        setUpButtonFunction()
    }

    private fun setUpButtonFunction(){
        setUpSignUpButton()
        setUpLoginTextButton()
    }

    private fun setUpSignUpButton(){
        binding.signUpButton.setOnClickListener { registerNewUser() }
    }

    private fun setUpLoginTextButton(){
        binding.loginText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun validInputFields():Boolean{
        var isValid = true
        val userName = binding.edtUserName.text.toString()
        val account = binding.edtAccount.text.toString()
        val pass = binding.edtPass.text.toString()
        val rePass = binding.edtSePass.text.toString()

        if ( userName.isEmpty() ) {
            binding.ipLayoutName.error = getString(R.string.fill_the_information)
            isValid = false
        }

        if ( !ValidationUtils.isValidEmail(account) ) {
            binding.ipLayoutAccount.error = getString(R.string.error_un_valid_email)
            isValid = false
        }
        if ( !ValidationUtils.isValidPass(pass) ){
            binding.ipLayoutPass.error = getString(R.string.error_un_valid_password)
            isValid = false
        }
        if ( pass != rePass ) {
            binding.ipLayoutSePass.error = getString(R.string.error_un_valid_re_password)
            isValid = false
        }
        return isValid
    }

    private fun registerNewUser(){
        val name = binding.edtUserName.text.toString()
        val email = binding.edtAccount.text.toString()
        val pass = binding.edtPass.text.toString()
        binding.progressBar.visibility = View.VISIBLE
        if ( !validInputFields() ) {
            binding.progressBar.visibility = View.INVISIBLE
            return
        }

        auth.createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener { task ->
                if ( task.isSuccessful ){

                    val user = auth.currentUser
                    user?.let {
                        // save data in firebase realtime
                        val userId = user.uid
                        val userData = UserData(name, email, pass)
                        database.collection("users").document(userId).set(userData)
                        binding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(this,"Register success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        startActivity(intent)
                        finish()
                    }
                }
                else{
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this,"Register Failed", Toast.LENGTH_SHORT).show()
                }
            }




    }
}