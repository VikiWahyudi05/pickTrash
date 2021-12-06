package com.sib.picktrash.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.sib.picktrash.LoginActivity
import com.sib.picktrash.RegisterActivity
import com.sib.picktrash.databinding.ActivityUserHomeBinding

class UserHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }

        binding.toUser.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    LaporanUserActivity::class.java
                )
            )
        })
    }

}