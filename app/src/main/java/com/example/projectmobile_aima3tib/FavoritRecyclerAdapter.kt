package com.example.projectmobile_aima3tib

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmobile_aima3tib.databinding.ItemFavoritBinding

class FavoritRecyclerAdapter(
    private val daftarProduk: List<ProdukEkraf>
) : RecyclerView.Adapter<FavoritRecyclerAdapter.FavoritViewHolder>() {

    inner class FavoritViewHolder(
        val binding: ItemFavoritBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritViewHolder {
        val binding = ItemFavoritBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoritViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FavoritViewHolder,
        position: Int
    ) {
        val produk = daftarProduk[position]

        holder.binding.tvCategoryFavorit.text = produk.kategori
        holder.binding.tvNamaFavorit.text = produk.nama
        holder.binding.tvDeskripsiFavorit.text = produk.deskripsi
        holder.binding.tvBanyakLike.text = produk.banyakLike
        holder.binding.ivGambarFavorit.setImageResource(produk.gambarResId)

        val formattedLike = when {
            produk.banyakLike.endsWith("Disukai", ignoreCase = true) -> {
                val parts = produk.banyakLike.split(" ")
                if (parts.isNotEmpty()) "${parts[0].uppercase()} Disukai" else produk.banyakLike
            }
            produk.banyakLike.endsWith("Menyukai", ignoreCase = true) -> {
                val count = produk.banyakLike.replace("Menyukai", "", ignoreCase = true).trim()
                "${count.uppercase()} Disukai"
            }
            else -> "${produk.banyakLike.trim().uppercase()} Disukai"
        }

        holder.binding.tvBanyakLike.text = formattedLike
        holder.binding.ivGambarFavorit.setImageResource(
            produk.gambarResId
        )
    }

    override fun getItemCount(): Int = daftarProduk.size
}
