package com.sib.picktrash

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.sib.picktrash.databinding.ActivityRegisterBinding
import com.sib.picktrash.user.UserHomeActivity
import java.util.HashMap

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore
    private var valid = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

        binding.apply {
            btnRegister.setOnClickListener{
                checkField(etNama)
                checkField(etEmail)
                checkField(etPassword)
                if (valid) {
                    auth.createUserWithEmailAndPassword(
                        etEmail.getText().toString(),
                        etPassword.getText().toString()
                    )
                        .addOnSuccessListener {
                            val nama = etNama.getText().toString()
                            val user = Firebase.auth.currentUser
                            val profileUpdates = userProfileChangeRequest {
                                displayName = nama
                            }

                            user!!.updateProfile(profileUpdates)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this@RegisterActivity, "Akun berhasil dibuat", Toast.LENGTH_SHORT)
                                            .show()
                                        val df = fStore.collection("Users").document(
                                            user.uid
                                        )
                                        val userInfo: MutableMap<String, Any> = HashMap()
                                        userInfo["UserName"] = nama
                                        userInfo["UserEmail"] = etEmail.getText().toString()
                                        userInfo["isUser"] = "1"
                                        df.set(userInfo)
                                        startActivity(Intent(applicationContext, UserHomeActivity::class.java))
                                        finish()
                                    }
                                }


                        }.addOnFailureListener {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Gagal membuat akun",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
            btnLogin.setOnClickListener {
                startActivity(
                    Intent(
                        applicationContext,
                        LoginActivity::class.java
                    )
                )
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
}