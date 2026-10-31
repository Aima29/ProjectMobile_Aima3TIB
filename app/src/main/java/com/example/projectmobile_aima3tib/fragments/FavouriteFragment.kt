package com.example.projectmobile_aima3tib.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.ProdukEkraf
import com.example.projectmobile_aima3tib.ProdukEkrafAdapter
import com.example.projectmobile_aima3tib.R
import com.example.projectmobile_aima3tib.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavouriteBinding.bind(view)

        val daftarFavorit = listOf(
            ProdukEkraf(
                "Rendang",
                "Kuliner khas Sumatera Barat",
                "Kuliner",
                R.drawable.rendang
            ),
            ProdukEkraf(
                "Anyaman Rotan",
                "Kerajinan dari rotan",
                "Kriya",
                R.drawable.anyaman
            ),
            ProdukEkraf(
                "Tenun Melayu",
                "Busana berbahan kain tenun",
                "Fashion",
                R.drawable.tenun
            )
        )

        val adapter = ProdukEkrafAdapter(
            requireContext(),
            daftarFavorit
        )

        binding.listViewFavorit.adapter = adapter

        binding.listViewFavorit.visibility =
            if (daftarFavorit.isEmpty()) View.GONE else View.VISIBLE

        binding.tvFavoritKosong.visibility =
            if (daftarFavorit.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}