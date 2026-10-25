package com.example.projectmobile_aima3tib.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.R

class FashionFragment : Fragment(R.layout.fragment_fashion) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .replace(R.id.containerFashion, DaftarProdukFragment.newInstance("FASHION"))
            .commit()
    }
}
