
package com.example.projectmobile_aima3tib.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.DetailActivity
import com.example.projectmobile_aima3tib.R
import com.example.projectmobile_aima3tib.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        binding.cardKuliner.setOnClickListener {
            bukaDetail("KULINER")
        }

        binding.cardKriya.setOnClickListener {
            bukaDetail("KRIYA")
        }

        binding.cardFashion.setOnClickListener {
            bukaDetail("FASHION")
        }
    }

    private fun bukaDetail(kategori: String) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("CATEGORY", kategori)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
