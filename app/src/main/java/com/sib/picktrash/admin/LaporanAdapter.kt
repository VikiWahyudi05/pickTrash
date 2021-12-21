package com.sib.picktrash.admin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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
                Glide.with(itemView.context)
                    .load(laporan.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(ivItemPhoto)
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context,DetailLaporanActivity::class.java)
                intent.putExtra(DetailLaporanActivity.EXTRA_NAME, laporan.name)
                intent.putExtra(DetailLaporanActivity.EXTRA_DESKRIPSI, laporan.description)
                intent.putExtra(DetailLaporanActivity.EXTRA_LATITUDE, laporan.latitude)
                intent.putExtra(DetailLaporanActivity.EXTRA_LONGITUDE, laporan.longitude)
                intent.putExtra(DetailLaporanActivity.EXTRA_IMAGE_URL, laporan.imageUrl)
                intent.putExtra(DetailLaporanActivity.EXTRA_ALAMAT, laporan.alamat)
                intent.putExtra(DetailLaporanActivity.EXTRA_STATUS, laporan.status)
                itemView.context.startActivity(intent)
            }
        }
    }
}