package com.sib.picktrash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sib.picktrash.databinding.ActivityRegisterBinding
import com.sib.picktrash.user.UserHomeActivity
import java.util.HashMap

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var store: FirebaseFirestore
    private var valid = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        store = FirebaseFirestore.getInstance()

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
                            val user = auth.currentUser
                            Toast.makeText(this@RegisterActivity, "Akun berhasil dibuat", Toast.LENGTH_SHORT)
                                .show()
                            val df = store.collection("Users").document(
                                user!!.uid
                            )
                            val userInfo: MutableMap<String, Any> = HashMap()
                            userInfo["UserName"] = etNama.getText().toString()
                            userInfo["UserEmail"] = etEmail.getText().toString()
                            userInfo["isUser"] = "1"
                            df.set(userInfo)
                            startActivity(Intent(applicationContext, UserHomeActivity::class.java))
                            finish()
                        }.addOnFailureListener {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Gagal membuat akun",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
            btnLogin.setOnClickListener(View.OnClickListener {
                startActivity(
                    Intent(
                        applicationContext,
                        LoginActivity::class.java
                    )
                )
            })
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