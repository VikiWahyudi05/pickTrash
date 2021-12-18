package com.sib.picktrash.admin

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sib.picktrash.R
import com.sib.picktrash.admin.laporan.FinishLaporanFragment
import com.sib.picktrash.admin.laporan.ListLaporanFragment
import com.sib.picktrash.admin.laporan.OnprogressLaporanFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)  {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.daftarLaporan, R.string.onprogress, R.string.finish)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> ListLaporanFragment()
            1 -> OnprogressLaporanFragment()
            2 -> FinishLaporanFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size
}