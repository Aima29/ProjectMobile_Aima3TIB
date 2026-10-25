package com.example.projectmobile_aima3tib

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.projectmobile_aima3tib.databinding.ItemProdukEkrafBinding

class ProdukEkrafAdapter(
    private val context: Context,
    private val daftarProduk: List<ProdukEkraf>
) : BaseAdapter() {

    override fun getCount(): Int = daftarProduk.size

    override fun getItem(position: Int): ProdukEkraf =
        daftarProduk[position]

    override fun getItemId(position: Int): Long =
        position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val binding = if (convertView == null) {
            ItemProdukEkrafBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        } else {
            ItemProdukEkrafBinding.bind(convertView)
        }

        val produk = getItem(position)

        binding.tvNamaProduk.text = produk.nama
        binding.tvDeskripsiProduk.text = produk.deskripsi
        binding.ivGambarProduk.setImageResource(produk.gambarResId)

        return binding.root
    }
}