package com.sib.picktrash.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sib.picktrash.databinding.ActivityLaporanBinding

class LaporanActivity : AppCompatActivity() {

    private lateinit var activityLaporanBinding: ActivityLaporanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityLaporanBinding = ActivityLaporanBinding.inflate(layoutInflater)
        setContentView(activityLaporanBinding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        activityLaporanBinding.laporanViewpager.adapter = sectionPagerAdapter
        activityLaporanBinding.laporanTabs.setupWithViewPager(activityLaporanBinding.laporanViewpager)
        supportActionBar?.elevation = 0f

    }
}