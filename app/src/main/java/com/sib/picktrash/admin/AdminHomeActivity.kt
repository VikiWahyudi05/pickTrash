package com.sib.picktrash.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.sib.picktrash.LoginActivity
import com.sib.picktrash.RegisterActivity
import com.sib.picktrash.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }


        binding.toAdmin.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    LaporanAdminActivity::class.java
                )
            )
        })
    }
}