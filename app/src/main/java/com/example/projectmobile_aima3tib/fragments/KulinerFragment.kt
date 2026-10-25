package com.example.projectmobile_aima3tib.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.R

class KulinerFragment : Fragment(R.layout.fragment_kuliner) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .replace(R.id.containerKuliner, DaftarProdukFragment.newInstance("KULINER"))
            .commit()
    }
}
