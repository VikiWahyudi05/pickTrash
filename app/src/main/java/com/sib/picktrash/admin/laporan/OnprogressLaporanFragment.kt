package com.sib.picktrash.admin.laporan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sib.picktrash.admin.DetailLaporanActivity
import com.sib.picktrash.admin.LaporanAdapter
import com.sib.picktrash.admin.LaporanModel
import com.sib.picktrash.databinding.FragmentListLaporanBinding
import com.sib.picktrash.databinding.FragmentOnprogressLaporanBinding

class OnprogressLaporanFragment : Fragment() {

    private lateinit var binding: FragmentOnprogressLaporanBinding
    var db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnprogressLaporanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            db.collection("report")
                .get()
                .addOnSuccessListener {
                    val listReport: ArrayList<LaporanModel> = ArrayList()
                    listReport.clear()
                    showProgressBar(true)
                    for (document in it) {
                        if (document.getString("status") as String == "1") {
                            showProgressBar(false)
                            listReport.add((LaporanModel(
                                document.data.get("name") as String,
                                document.data.get("description") as String,
                                document.data.get("latitude") as Double,
                                document.data.get("longitude") as Double,
                                document.data.get("imageUrl") as String,
                                document.data.get("alamat") as String,
                                document.data.get("status") as String
                            )))
                        }
                        Log.i("TAG", it.documents[0]["status"].toString())
                        val laporanAdapter = LaporanAdapter(listReport)
                        binding.rvLaporan.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = laporanAdapter
                        }

                    }


                }
                .addOnFailureListener {
                    Log.v("TAG", "gagal mengambil data")
                }
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

}