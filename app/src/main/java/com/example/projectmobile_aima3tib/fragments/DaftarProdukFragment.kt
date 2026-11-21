
package com.example.projectmobile_aima3tib.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.DetailActivity
import com.example.projectmobile_aima3tib.ProdukEkraf
import com.example.projectmobile_aima3tib.ProdukEkrafAdapter
import com.example.projectmobile_aima3tib.R
import com.example.projectmobile_aima3tib.databinding.FragmentDaftarProdukBinding

class DaftarProdukFragment : Fragment(R.layout.fragment_daftar_produk) {

    private var _binding: FragmentDaftarProdukBinding? = null
    private val binding get() = _binding!!

    private var kategori: String = "Kuliner"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kategori = arguments?.getString("KATEGORI") ?: "Kuliner"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDaftarProdukBinding.bind(view)

        val semuaKarya = listOf(
            ProdukEkraf(
                "Rendang",
                "Kuliner khas Sumatera Barat",
                "Kuliner",
                R.drawable.rendang,
                "12k Menyukai"
            ),
            ProdukEkraf(
                "Pempek",
                "Olahan ikan khas Palembang",
                "Kuliner",
                R.drawable.pempek,
                "10k Menyukai"
            ),
            ProdukEkraf(
                "Gulai Ikan Patin",
                "Kuliner khas Riau",
                "Kuliner",
                R.drawable.gulaipatin,
                "15k Menyukai"
            ),
            ProdukEkraf(
                "Anyaman Rotan",
                "Kerajinan dari rotan",
                "Kriya",
                R.drawable.anyaman,
                "100k Menyukai"
            ),
            ProdukEkraf(
                "Aksesori Lokal",
                "Aksesori buatan pengrajin",
                "Kriya",
                R.drawable.aksesoris,
                "120k Menyukai"
            ),
            ProdukEkraf(
                "Ukiran Kayu",
                "Kerajinan seni ukir",
                "Kriya",
                R.drawable.ukirankayu,
                "10k Menyukai"
            ),
            ProdukEkraf(
                "Batik Fashion",
                "Busana bermotif batik",
                "Fashion",
                R.drawable.bajubatik,
                "125k Menyukai"
            ),
            ProdukEkraf(
                "Tenun Melayu",
                "Busana berbahan kain tenun",
                "Fashion",
                R.drawable.tenun,
                "5k Menyukai"
            ),
            ProdukEkraf(
                "Batik",
                "Karya seni pada kain",
                "Fashion",
                R.drawable.batik,
                "15k Menyukai"
            )
        )

        // Filter produk berdasarkan kategori yang dipilih
        val dataKarya = semuaKarya.filter {
            it.kategori.equals(kategori, ignoreCase = true)
        }

        binding.tvJudulDaftar.text = "Produk $kategori"

        val adapter = ProdukEkrafAdapter(
            requireContext(),
            dataKarya
        )

        binding.listViewKarya.adapter = adapter

        binding.listViewKarya.setOnItemClickListener { _, _, position, _ ->
            val produk = dataKarya[position]

            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("NAMA_PRODUK", produk.nama)
            intent.putExtra("CATEGORY", produk.kategori)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(kategori: String) =
            DaftarProdukFragment().apply {
                arguments = Bundle().apply {
                    putString("KATEGORI", kategori)
                }
            }
    }
}
