package com.sib.picktrash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sib.picktrash.admin.AdminHomeActivity
import com.sib.picktrash.databinding.ActivityLoginBinding
import com.sib.picktrash.user.UserHomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore
    private var valid = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

        binding.apply {
            btnLogin.setOnClickListener {
                checkField(etEmail)
                checkField(etPassword)

                if (valid) {
                    auth.signInWithEmailAndPassword(
                        etEmail.getText().toString(),
                        etPassword.getText().toString()
                    )
                        .addOnSuccessListener { authResult ->
                            Toast.makeText(this@LoginActivity, "Sukses Login", Toast.LENGTH_SHORT)
                                .show()
                            checkUserAccessLevel(authResult.user!!.uid)
                        }.addOnFailureListener {
                            Toast.makeText(
                                this@LoginActivity,
                                "Password salah",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }

            btnRegister.setOnClickListener {
                startActivity(
                    Intent(
                        applicationContext,
                        RegisterActivity::class.java
                    )
                )
            }
        }
    }

    private fun checkUserAccessLevel(uid: String) {
        val df = fStore.collection("Users").document(uid)

        df.get().addOnSuccessListener { documentSnapshot ->
            Log.d("TAG", "onSuccess: " + documentSnapshot.data)

            if (documentSnapshot.getString("isUser") != null) {
                Toast.makeText(this@LoginActivity, "Selemat datang User", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, UserHomeActivity::class.java))
                finish()
            }
            if (documentSnapshot.getString("isAdmin") != null) {
                Toast.makeText(this@LoginActivity, "Selamat datang Admin", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, AdminHomeActivity::class.java))
                finish()
            }
        }

    }

    fun checkField(textField: EditText?): Boolean {
        if (textField!!.text.toString().isEmpty()) {
            textField.error = "Error"
            valid = false
        } else {
            valid = true
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            checkUserAccessLevel(FirebaseAuth.getInstance().currentUser!!.uid)
        }
    }
}