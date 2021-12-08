package com.sib.picktrash.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.sib.picktrash.databinding.ActivityLaporanUserBinding

class LaporanUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporanUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            createReport();
        }



    }

    private fun createReport() {
        binding.apply {
            val name = detailName.text.toString()
            val description = detailDescription.text.toString()

            if (name.isEmpty()) detailName.error = "Nama tidak boleh kosong"
            if (description.isEmpty()) detailDescription.error = "Deskripsi tidak boleh kosong"
            else {
                saveReport()

            }
        }
    }

    private fun saveReport() {
        val db = FirebaseFirestore.getInstance()

        val hashMap : HashMap<String, Any> = HashMap()
        binding.apply {
            hashMap["name"] = detailName.text.toString()
            hashMap["description"] = detailDescription.text.toString()
        }
        db.collection("report")
            .add(hashMap)
            .addOnSuccessListener {
                Toast.makeText(this, "Laporan Terkirim", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Laporan Gagal Terkirim", Toast.LENGTH_SHORT).show()
            }

    }


}