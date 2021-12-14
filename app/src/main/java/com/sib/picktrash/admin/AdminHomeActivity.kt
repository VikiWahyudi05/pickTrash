package com.sib.picktrash.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.sib.picktrash.LoginActivity
import com.sib.picktrash.RegisterActivity
import com.sib.picktrash.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminHomeBinding
    private lateinit var fStore: FirebaseFirestore

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fStore = FirebaseFirestore.getInstance()

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


        auth = FirebaseAuth.getInstance()
        val fUser = auth.currentUser

        binding.apply {
            tvNamaAdmin.setText(fUser?.displayName)
            fUser?.displayName?.let { Log.i("TAG", it) }
        }

    }

}