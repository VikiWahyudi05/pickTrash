package com.sib.picktrash.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sib.picktrash.databinding.ActivityLaporanUserBinding

class LaporanUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporanUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}