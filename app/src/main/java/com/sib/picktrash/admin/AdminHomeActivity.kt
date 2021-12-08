package com.sib.picktrash.admin

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
    private lateinit var store: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        store = FirebaseFirestore.getInstance()

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

        getAllData()
    }

    fun getAllData(){
        store.collection("Users").get().addOnSuccessListener {
//            for (result in it.documents){
//                Log.i("firedata","${result.data?.values}")
//            }
            Log.i("firedata","${it.documents.get(0).data?.get("UserName")}")
            binding.tvNamaAdmin.text = "${it.documents.get(0).data?.get("UserName")}"
        }
    }
}