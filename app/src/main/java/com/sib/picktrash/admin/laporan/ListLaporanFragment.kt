package com.sib.picktrash.admin.laporan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sib.picktrash.admin.LaporanAdapter
import com.sib.picktrash.admin.LaporanModel
import com.sib.picktrash.databinding.FragmentListLaporanBinding

class ListLaporanFragment : Fragment() {

    private lateinit var listBinding: FragmentListLaporanBinding
    var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListLaporanBinding.inflate(layoutInflater, container, false)
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null){
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
                    val laporanAdapter = LaporanAdapter(listReport)
                    listBinding.rvLaporan.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = laporanAdapter
                    }
                }
                .addOnFailureListener {
                    Log.v("TAG", "gagal mengambil data")
                }
        }
    }
}