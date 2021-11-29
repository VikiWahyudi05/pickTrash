package com.sib.picktrash.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sib.picktrash.databinding.ActivityHomeBinding

class UserHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}