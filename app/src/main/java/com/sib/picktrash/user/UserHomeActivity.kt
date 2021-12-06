package com.sib.picktrash.user

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
import com.sib.picktrash.databinding.ActivityUserHomeBinding

class UserHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding

    private lateinit var fStore: FirebaseFirestore

    var user: List<String> = ArrayList()

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

        binding.toUser.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    LaporanUserActivity::class.java
                )
            )
        })
    }

    private fun readData() {
        user = ArrayList()

        fStore.collection("Users")
            .get()
            .addOnSuccessListener {
                (user as ArrayList<String>).clear()
//                for (document in it) {
//
////                    (user as ArrayList<String>).add(
//
////                        document.data.get("UserName") as String
////                    )
//                    Log.d("fire", "${document.data.get("UserName")}")
//                    Log.d("fire", "${document.data.get("UserEmail")}")
//                    Log.d("fire", "${document.data.get("isUser")}")
//
//                }

                (user as ArrayList<String>).clear()
                for (document in it) {
                    (user as ArrayList<String>).add(
                        binding.apply {
                            nama.text = document.data.get("UserName") as CharSequence
                            document.data.get("UserEmail") as CharSequence
                            document.data.get("isUser") as CharSequence
                        }.toString()
                    )

                    Log.d(TAG, "${it.documents}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

}