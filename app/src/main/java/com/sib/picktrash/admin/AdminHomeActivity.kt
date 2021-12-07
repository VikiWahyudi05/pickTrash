package com.sib.picktrash.admin

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sib.picktrash.LoginActivity
import com.sib.picktrash.RegisterActivity
import com.sib.picktrash.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding

    private lateinit var fStore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fStore = FirebaseFirestore.getInstance()


        readData()


        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }

        binding.toAdmin.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    LaporanAdminActivity::class.java
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

                    if (document.getString("isAdmin") != null) {
                        listUser.add(
                            binding.apply {
                                etNama.text = document.data.getValue("UserName").toString()
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