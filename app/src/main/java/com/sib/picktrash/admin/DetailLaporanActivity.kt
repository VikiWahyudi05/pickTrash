package com.sib.picktrash.admin

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
    }
}