package com.sib.picktrash.admin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sib.picktrash.databinding.ActivityDetailLaporanBinding

class DetailLaporanActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESKRIPSI = "extra_deskripsi"
        const val EXTRA_LATITUDE = "extra_latitude"
        const val EXTRA_LONGITUDE = "extra_longitude"
        const val EXTRA_IMAGE_URL = "extra_img_url"
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
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)


        binding.apply {
            tvNamaPelapor.text = namaPelapor
            tvDeskripsiDetail.text = deskripsi
            tvLatitude.text = "Latitude : "+latitude.toString()
            tvLongitude.text = "Longitude : "+longitude.toString()

            Glide.with(this@DetailLaporanActivity)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgDetail)
        }


        binding.btnProses.setOnClickListener {
            val navigationIntentUri: Uri =
                Uri.parse("google.navigation:q=$latitude,$longitude") //creating intent with latlng
            val mapIntent = Intent(Intent.ACTION_VIEW, navigationIntentUri)
            val intent = Intent(it.context,DetailLaporanActivity::class.java)
            intent.putExtra(EXTRA_NAME, namaPelapor)
            intent.putExtra(EXTRA_DESKRIPSI, deskripsi)
            intent.putExtra(EXTRA_LATITUDE, latitude)
            intent.putExtra(EXTRA_LONGITUDE, longitude)
            intent.putExtra(EXTRA_IMAGE_URL, imageUrl)
            it.context.startActivity(intent)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

    }
}