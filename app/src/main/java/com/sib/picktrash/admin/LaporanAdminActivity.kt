package com.sib.picktrash.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sib.picktrash.databinding.ActivityLaporanAdminBinding

class LaporanAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporanAdminBinding

    var db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Firebase.firestore
        getData()
    }

    private fun getData() {
        db.collection("report")
            .get()
            .addOnSuccessListener {
                val listReport: ArrayList<LaporanModel> = ArrayList()
                listReport.clear()

                for (document in it) {
                    listReport.add((LaporanModel(
                        document.data.get("name") as String,
                        document.data.get("description") as String,
                        document.data.get("latitude") as Double,
                        document.data.get("longitude") as Double,
                        document.data.get("imageUrl") as String
                    )))
                }
                var laporanAdapter = LaporanAdapter(listReport)
                binding.rvLaporan.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = laporanAdapter
                }
            }
            .addOnFailureListener {
                Log.v("TAG", "gagal mengambil data")
            }
    }
}