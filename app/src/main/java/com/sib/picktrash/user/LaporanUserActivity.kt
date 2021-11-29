package com.sib.picktrash.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sib.picktrash.databinding.ActivityLaporanBinding

class LaporanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}