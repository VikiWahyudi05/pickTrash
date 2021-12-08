package com.sib.picktrash.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.sib.picktrash.databinding.ActivityLaporanUserBinding

class LaporanUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USER = "extra_user"
        const val EXTRA_LATITUDE = "extra_latitude"
        const val EXTRA_LONGITUDE = "extra_longitude"
    }

    private lateinit var binding: ActivityLaporanUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_USER)

        binding.detailName.setText(name)

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

        val latitude = intent.getDoubleExtra(EXTRA_LATITUDE, 0.0)
        val longitude = intent.getDoubleExtra(EXTRA_LONGITUDE, 0.0)

        val hashMap : HashMap<String, Any> = HashMap()

        binding.apply {
            hashMap["name"] = detailName.text.toString()
            hashMap["description"] = detailDescription.text.toString()
            hashMap["latitude"] = latitude
            hashMap["longitude"] = longitude
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