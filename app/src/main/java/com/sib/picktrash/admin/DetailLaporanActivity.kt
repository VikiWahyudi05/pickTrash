package com.sib.picktrash.admin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.sib.picktrash.databinding.ActivityDetailLaporanBinding

class DetailLaporanActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESKRIPSI = "extra_deskripsi"
        const val EXTRA_LATITUDE = "extra_latitude"
        const val EXTRA_LONGITUDE = "extra_longitude"
    }

    private lateinit var binding: ActivityDetailLaporanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val namaPelapor = intent.getStringExtra(EXTRA_NAME)
        val deskripsi = intent.getStringExtra(EXTRA_DESKRIPSI)
        val latitude = intent.getDoubleExtra(EXTRA_LATITUDE,0.0)
        val longitude = intent.getDoubleExtra(EXTRA_LONGITUDE,0.0)

        binding.tvNamaPelapor.text = namaPelapor
        binding.tvDeskripsiDetail.text = deskripsi
        binding.tvLatitude.text = "Latitude : "+latitude.toString()
        binding.tvLongitude.text = "Longitude : "+longitude.toString()

        binding.btnProses.setOnClickListener {
            val navigationIntentUri: Uri =
                Uri.parse("google.navigation:q=$latitude,$longitude") //creating intent with latlng
            val mapIntent = Intent(Intent.ACTION_VIEW, navigationIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

    }
}