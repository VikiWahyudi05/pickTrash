package com.sib.picktrash.admin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sib.picktrash.databinding.ActivityDetailLaporanBinding

class DetailLaporanActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESKRIPSI = "extra_deskripsi"
        const val EXTRA_LATITUDE = "extra_latitude"
        const val EXTRA_LONGITUDE = "extra_longitude"
        const val EXTRA_IMAGE_URL = "extra_img_url"
        const val EXTRA_ALAMAT = "extra_alamat"
        const val EXTRA_STATUS = "extra_status"
    }

    private lateinit var binding: ActivityDetailLaporanBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val namaPelapor = intent.getStringExtra(EXTRA_NAME)
        val deskripsi = intent.getStringExtra(EXTRA_DESKRIPSI)
        val latitude = intent.getDoubleExtra(EXTRA_LATITUDE, 0.0)
        val longitude = intent.getDoubleExtra(EXTRA_LONGITUDE, 0.0)
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        val alamat = intent.getStringExtra(EXTRA_ALAMAT)
        val status = intent.getStringExtra(EXTRA_STATUS)


        binding.apply {
            tvNamaPelapor.text = namaPelapor
            tvDeskripsiDetail.text = deskripsi
            tvLatitude.text = "Latitude : " + latitude.toString()
            tvLongitude.text = "Longitude : " + longitude.toString()
            tvAlamat.text = alamat

            Glide.with(this@DetailLaporanActivity)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgDetail)
        }

        if (status == "0") {
            binding.btnProses.visibility = View.VISIBLE
        } else if (status == "2") {
            binding.btnSelesai.visibility = View.GONE
        } else {
            binding.btnSelesai.visibility = View.VISIBLE
        }

        binding.btnSelesai.setOnClickListener {
            val db = FirebaseFirestore.getInstance()

            val query = db.collection("report").whereEqualTo("imageUrl", imageUrl).get()

            val status = hashMapOf(
                "status" to "2"
            )

            query.addOnSuccessListener {
                for (document in it) {
                    db.collection("report").document(document.id).set(status, SetOptions.merge())
                    Log.i(
                        "TAG",
                        "${
                            db.collection("report").document(document.id)
                                .set(status, SetOptions.merge())
                        }"
                    )

                }
            }
        }


        binding.btnProses.setOnClickListener {
            val navigationIntentUri: Uri =
                Uri.parse("google.navigation:q=$latitude,$longitude") //creating intent with latlng
            val mapIntent = Intent(Intent.ACTION_VIEW, navigationIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

            val db = FirebaseFirestore.getInstance()

            val query = db.collection("report").whereEqualTo("imageUrl", imageUrl).get()

            val status = hashMapOf(
                "status" to "1"
            )

            query.addOnSuccessListener {
                for (document in it) {
                    db.collection("report").document(document.id).set(status, SetOptions.merge())
                    Log.i(
                        "TAG",
                        "${
                            db.collection("report").document(document.id)
                                .set(status, SetOptions.merge())
                        }"
                    )

                }
            }

        }

    }


}