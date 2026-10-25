
package com.example.projectmobile_aima3tib.fragments

import android.os.Bundle
import android.view.View
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.R
import com.example.projectmobile_aima3tib.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouriteBinding.bind(view)

        // Data contoh untuk tampilan awal
        val daftarFavorit = listOf(
            mapOf(
                "nama" to "Rendang",
                "deskripsi" to "Kuliner khas Sumatera Barat"
            ),
            mapOf(
                "nama" to "Anyaman Rotan",
                "deskripsi" to "Kerajinan tangan lokal"
            ),
            mapOf(
                "nama" to "Tenun Melayu",
                "deskripsi" to "Karya fashion lokal"
            )
        )

        val adapter = SimpleAdapter(
            requireContext(),
            daftarFavorit,
            android.R.layout.simple_list_item_2,
            arrayOf("nama", "deskripsi"),
            intArrayOf(
                android.R.id.text1,
                android.R.id.text2
            )
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
