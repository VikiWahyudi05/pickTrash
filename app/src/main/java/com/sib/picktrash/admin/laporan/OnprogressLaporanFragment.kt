package com.sib.picktrash.admin.laporan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sib.picktrash.admin.DetailLaporanActivity
import com.sib.picktrash.databinding.FragmentOnprogressLaporanBinding

class OnprogressLaporanFragment : Fragment() {
    private lateinit var binding: FragmentOnprogressLaporanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnprogressLaporanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}