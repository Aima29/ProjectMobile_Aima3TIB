package com.example.projectmobile_aima3tib.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.R

class KriyaFragment : Fragment(R.layout.fragment_kriya) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .replace(R.id.containerKriya, DaftarProdukFragment.newInstance("KRIYA"))
            .commit()
    }
}
