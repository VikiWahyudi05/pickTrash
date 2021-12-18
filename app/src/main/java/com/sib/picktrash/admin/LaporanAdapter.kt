package com.sib.picktrash.admin

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sib.picktrash.databinding.ItemLaporanBinding

class LaporanAdapter (private val laporan:List<LaporanModel>) : RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanViewHolder {
        val binding = ItemLaporanBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return LaporanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaporanViewHolder, position: Int) {
        holder.bind(laporan[position])
    }

    override fun getItemCount(): Int = laporan.size

    inner class LaporanViewHolder(private val binding: ItemLaporanBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(laporan: LaporanModel) {
            with(binding) {
                tvNama.text = laporan.name
                tvDeskripsi.text = laporan.description
                itemView.setOnClickListener {
                    Log.i("laporan",laporan.latitude.toString())
                }
            }
        }
    }
}