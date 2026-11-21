package com.example.projectmobile_aima3tib

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.databinding.ActivityBaseBinding
import com.example.projectmobile_aima3tib.fragments.DaftarProdukFragment
import com.example.projectmobile_aima3tib.fragments.FavouriteFragment
import com.example.projectmobile_aima3tib.fragments.HomeFragment
import com.example.projectmobile_aima3tib.fragments.ProfilFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

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
        setupBadge()
        setupCategoryTabs()
        setupLogout()
        setupOptionMenu()

        if (savedInstanceState == null) {
            binding.bottomNavView.selectedItemId = R.id.menu_home
            tampilkanHalaman(HomeFragment(), true)
            selectCategoryTab(0)
        }
    }

    // Menu option titik tiga
    private fun setupOptionMenu() {
        binding.btnOptionMenu.setOnClickListener { view ->

            val popupMenu = PopupMenu(this, view)

            popupMenu.menuInflater.inflate(
                R.menu.menu_option,
                popupMenu.menu
            )

            popupMenu.setOnMenuItemClickListener { item ->

                when (item.itemId) {

                    R.id.menu_informasi_ekraf -> {
                        startActivity(
                            Intent(
                                this,
                                WebViewActivity::class.java
                            )
                        )
                        true
                    }

                    R.id.menu_tentang_aplikasi -> {
                        startActivity(
                            Intent(
                                this,
                                TentangAplikasiActivity::class.java
                            )
                        )
                        true
                    }

                    else -> false
                }
            }

            popupMenu.show()
        }
    }

    // Bottom navigation
    private fun setupBottomNavigation() {
        binding.bottomNavView.isItemActiveIndicatorEnabled = false
        binding.bottomNavView.itemActiveIndicatorColor = ColorStateList.valueOf(Color.TRANSPARENT)

        binding.bottomNavView.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.menu_home -> {
                    tampilkanHalaman(HomeFragment(), true)
                    selectCategoryTab(0)
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

    private fun setupBadge() {
        val badge = binding.bottomNavView.getOrCreateBadge(R.id.menu_favorit)

        badge.isVisible = true
        badge.number = 3
        badge.backgroundColor = Color.parseColor("#E27746")
        badge.badgeTextColor = Color.WHITE
    }

    // Menampilkan halaman
    private fun tampilkanHalaman(
        fragment: Fragment,
        tampilkanKomponenHome: Boolean
    ) {
        aturTampilanHome(tampilkanKomponenHome)
        loadFragment(fragment)
    }

    // Mengatur komponen Home
    private fun aturTampilanHome(tampilkan: Boolean) {

        val visibility = if (tampilkan) {
            View.VISIBLE
        } else {
            View.GONE
        }

        binding.toolbar.visibility = visibility
        binding.cardBanner.visibility = visibility
        binding.cardSearch.visibility = visibility
        binding.tvSectionTitle.visibility = visibility
        binding.tabLayoutKategori.visibility = visibility
    }

    // Setup TabLayout kategori
    // Kode ini membuat sebuah daftar kategori menggunakan listOf().
    // Daftar tersebut berisi empat nama kategori yang akan ditampilkan pada TabLayout.
    private fun setupCategoryTabs() {
        val tabTitles = listOf("Unggulan", "Kuliner", "Kriya", "Fashion")

        //Bagian ini menggunakan perulangan for untuk memproses setiap tab yang tersedia.
        for (i in 0 until binding.tabLayoutKategori.tabCount) {
            val tab = binding.tabLayoutKategori.getTabAt(i)
            val customView = LayoutInflater.from(this).inflate(R.layout.item_tab_kategori, null) as TextView
            customView.text = tabTitles.getOrNull(i) ?: tab?.text ?: ""
            tab?.customView = customView
        }

        binding.tabLayoutKategori.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> tampilkanHalaman(HomeFragment(), true)
                    1 -> tampilkanHalaman(DaftarProdukFragment.newInstance("KULINER"), true)
                    2 -> tampilkanHalaman(DaftarProdukFragment.newInstance("KRIYA"), true)
                    3 -> tampilkanHalaman(DaftarProdukFragment.newInstance("FASHION"), true)
                }
                updateTabStyles()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                updateTabStyles()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        updateTabStyles()
    }

    private fun selectCategoryTab(position: Int) {
        val tab = binding.tabLayoutKategori.getTabAt(position)
        tab?.select()
        updateTabStyles()
    }

    // Mengatur gaya visual tab (Selected / Unselected)
    private fun updateTabStyles() {
        val selectedPosition = binding.tabLayoutKategori.selectedTabPosition

        for (i in 0 until binding.tabLayoutKategori.tabCount) {
            val tab = binding.tabLayoutKategori.getTabAt(i)
            val customView = tab?.customView as? TextView ?: continue

            if (i == selectedPosition) {
                customView.setBackgroundResource(R.drawable.bg_tab_selected)
                customView.setTextColor(Color.WHITE)
            } else {
                customView.setBackgroundResource(R.drawable.bg_tab_unselected)
                customView.setTextColor(Color.parseColor("#3E2B2F"))
            }
        }
    }

    // Tombol Keluar
    private fun setupLogout() {

        binding.btnLogout.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Keluar")
                .setMessage(
                    "Apakah Anda yakin ingin keluar dari aplikasi Ekrafku?"
                )
                .setPositiveButton("Ya") { _, _ ->

                    getSharedPreferences(
                        "user_pref", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply()

                    val intent = Intent(
                        this,
                        LoginActivity::class.java
                    )

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

    // Memuat fragment
    private fun loadFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}