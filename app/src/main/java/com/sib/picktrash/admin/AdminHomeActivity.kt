package com.sib.picktrash.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sib.picktrash.R

class AdminHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
        getActionBar()?.hide()
    }
}