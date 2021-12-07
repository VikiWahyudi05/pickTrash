package com.sib.picktrash.user

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sib.picktrash.LoginActivity
import com.sib.picktrash.RegisterActivity
import com.sib.picktrash.admin.AdminHomeActivity
import com.sib.picktrash.databinding.ActivityUserHomeBinding

class UserHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding
    private lateinit var fStore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fStore = FirebaseFirestore.getInstance()


        readData()

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
    }

    private fun readData() {

        fStore.collection("Users")
            .get()
            .addOnSuccessListener {
                var listUser: ArrayList<String> = ArrayList()
                listUser.clear()

                for (document in it) {

                    if (document.getString("isUser") != null) {
                        listUser.add(
                            binding.apply {
                                nama.text = document.data.getValue("UserName").toString()
                                email.text = document.data.get("UserEmail").toString()
                            }.toString()
                        )
                    }

                    Log.d(TAG, "${it.documents}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }



}