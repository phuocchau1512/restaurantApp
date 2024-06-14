package com.example.restaurantapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivityLoginBinding
import com.example.restaurantapp.helper.ValidationUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        database = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        setUpFunctionButton()
    }


    private fun setUpFunctionButton(){
        setUpLoginButton()
        setUpSignInTextButton()
    }

    private fun setUpLoginButton(){
        binding.loginButton.setOnClickListener { loginUser() }
    }

    private fun setUpSignInTextButton(){
        binding.signUpText.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun validInputFields():Boolean{
        var isValid = true
        val account = binding.edtAccount.text.toString()
        val pass = binding.edtPass.text.toString()



        if ( !ValidationUtils.isValidEmail(account) ) {
            binding.ipLayoutAccount.error = getString(R.string.error_un_valid_email)
            isValid = false
        }
        if ( !ValidationUtils.isValidPass(pass) ){
            binding.ipLayoutPass.error = getString(R.string.error_un_valid_password)
            isValid = false
        }

        return isValid
    }

    private fun loginUser() {

        val email = binding.edtAccount.text.toString()
        val pass = binding.edtPass.text.toString()
        binding.progressBar.visibility = View.VISIBLE
        if (!validInputFields()) {
            binding.progressBar.visibility = View.INVISIBLE
            return
        }

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {task ->
            if ( task.isSuccessful ){
                binding.progressBar.visibility = View.INVISIBLE
                startActivity(Intent(this,MainActivity::class.java))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this,"email or password is wrong",Toast.LENGTH_SHORT).show()
            }
        }

    }

}