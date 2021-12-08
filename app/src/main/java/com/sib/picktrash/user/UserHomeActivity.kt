package com.sib.picktrash.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.sib.picktrash.LoginActivity
import com.sib.picktrash.databinding.ActivityUserHomeBinding




class UserHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding
    private lateinit var fStore: FirebaseFirestore
    private lateinit var fAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fStore = FirebaseFirestore.getInstance()
        fAuth = FirebaseAuth.getInstance()
        val fUser: FirebaseUser? = fAuth.currentUser

        binding.apply {
            email.setText(fUser?.email)
            nama.setText(fUser?.displayName)
        }

        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }

        binding.toUser.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    LaporanUserActivity::class.java
                )
            )
        }

        binding.ivVerifikasi.setOnClickListener {
            fUser?.sendEmailVerification()?.addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this@UserHomeActivity,
                        "Email verifikasi telah dikirim",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@UserHomeActivity,
                        "${it.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

    }

}

