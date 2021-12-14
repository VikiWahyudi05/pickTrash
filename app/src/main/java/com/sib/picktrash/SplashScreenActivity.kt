package com.sib.picktrash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sib.picktrash.databinding.ActivitySplashscreenBinding
import java.lang.Exception

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val splashScreen = object : Thread() {
            override fun run() {
                try {
                    sleep(EXTRA_TIME)

                    val intent = Intent(baseContext, LoginActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        splashScreen.start()
    }

    companion object{
        const val EXTRA_TIME = 2000L
    }

}