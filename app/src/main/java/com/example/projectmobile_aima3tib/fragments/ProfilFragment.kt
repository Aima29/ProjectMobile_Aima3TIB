
package com.example.projectmobile_aima3tib.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.R
import com.example.projectmobile_aima3tib.databinding.FragmentProfilBinding

class ProfilFragment : Fragment(R.layout.fragment_profil) {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfilBinding.bind(view)

        val preferences = requireContext()
            .getSharedPreferences("user_pref", 0)

        val nama = preferences.getString("nama", "Pengguna Ekrafku")
            ?: "Pengguna Ekrafku"

        val email = preferences.getString("email", "Belum tersedia")
            ?: "Belum tersedia"

        binding.tvNamaProfil.text = nama
        binding.tvNamaLengkap.text = nama
        binding.tvEmailProfil.text = email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
