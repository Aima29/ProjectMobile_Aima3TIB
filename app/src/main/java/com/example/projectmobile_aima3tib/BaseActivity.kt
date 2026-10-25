
package com.example.projectmobile_aima3tib

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.databinding.ActivityBaseBinding
import com.example.projectmobile_aima3tib.fragments.DaftarProdukFragment
import com.example.projectmobile_aima3tib.fragments.FavouriteFragment
import com.example.projectmobile_aima3tib.fragments.HomeFragment
import com.example.projectmobile_aima3tib.fragments.ProfilFragment
import com.google.android.material.snackbar.Snackbar

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    private val warnaAktif = Color.parseColor("#C94E12")
    private val warnaNormal = Color.parseColor("#F2E6DC")
    private val warnaTeks = Color.parseColor("#3E2B2F")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                0
            )

            insets
        }

        setupBottomNavigation()
        setupCategoryChips()
        setupLogout()

        if (savedInstanceState == null) {
            binding.bottomNavView.selectedItemId = R.id.menu_home
            tampilkanHalaman(HomeFragment(), true)
            updateChipStyles(binding.btnChipSemua)
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    tampilkanHalaman(HomeFragment(), true)
                    updateChipStyles(binding.btnChipSemua)
                }

                R.id.menu_favorit -> {
                    tampilkanHalaman(FavouriteFragment(), false)
                }

                R.id.menu_profil -> {
                    tampilkanHalaman(ProfilFragment(), false)
                }

                else -> return@setOnItemSelectedListener false
            }

            true
        }
    }

    private fun tampilkanHalaman(
        fragment: Fragment,
        tampilkanKomponenHome: Boolean
    ) {
        aturTampilanHome(tampilkanKomponenHome)
        loadFragment(fragment)
    }

    private fun aturTampilanHome(tampilkan: Boolean) {
        val visibility = if (tampilkan) View.VISIBLE else View.GONE

        binding.toolbar.visibility = visibility
        binding.cardBanner.visibility = visibility
        binding.cardSearch.visibility = visibility
        binding.tvSectionTitle.visibility = visibility

        binding.btnChipSemua.visibility = visibility
        binding.btnChipKuliner.visibility = visibility
        binding.btnChipKriya.visibility = visibility
        binding.btnChipFashion.visibility = visibility
    }


    private fun setupCategoryChips() {
        binding.btnChipSemua.setOnClickListener {
            tampilkanHalaman(HomeFragment(), true)
            updateChipStyles(binding.btnChipSemua)
        }

        binding.btnChipKuliner.setOnClickListener {
            tampilkanHalaman(
                DaftarProdukFragment.newInstance("KULINER"),
                true
            )
            updateChipStyles(binding.btnChipKuliner)
        }

        binding.btnChipKriya.setOnClickListener {
            tampilkanHalaman(
                DaftarProdukFragment.newInstance("KRIYA"),
                true
            )
            updateChipStyles(binding.btnChipKriya)
        }

        binding.btnChipFashion.setOnClickListener {
            tampilkanHalaman(
                DaftarProdukFragment.newInstance("FASHION"),
                true
            )
            updateChipStyles(binding.btnChipFashion)
        }
    }


    private fun updateChipStyles(selectedButton: Button) {
        val chips = listOf(
            binding.btnChipSemua,
            binding.btnChipKuliner,
            binding.btnChipKriya,
            binding.btnChipFashion
        )

        chips.forEach { chip ->
            val selected = chip == selectedButton

            chip.backgroundTintList = ColorStateList.valueOf(
                if (selected) warnaAktif else warnaNormal
            )

            chip.setTextColor(
                if (selected) Color.WHITE else warnaTeks
            )
        }
    }

    private fun setupLogout() {
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Keluar")
                .setMessage(
                    "Apakah Anda yakin ingin keluar dari aplikasi Ekrafku?"
                )
                .setPositiveButton("Ya") { _, _ ->
                    getSharedPreferences("user_pref", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply()

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()

                    Snackbar.make(
                        binding.root,
                        "Batal keluar dari aplikasi",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                .show()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
