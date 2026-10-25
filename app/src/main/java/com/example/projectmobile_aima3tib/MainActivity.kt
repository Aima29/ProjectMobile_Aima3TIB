package com.example.projectmobile_aima3tib

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.projectmobile_aima3tib.databinding.ActivityMainBinding
import com.example.projectmobile_aima3tib.fragments.FashionFragment
import com.example.projectmobile_aima3tib.fragments.HomeFragment
import com.example.projectmobile_aima3tib.fragments.KriyaFragment
import com.example.projectmobile_aima3tib.fragments.KulinerFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ViewBinding digunakan untuk mengakses komponen pada activity_main.xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menyesuaikan layout dengan status bar dan navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        val user = intent.getStringExtra("username")
        val pass = intent.getStringExtra("password")
        val umur = intent.getIntExtra("umur", 0)

        // Saat MainActivity pertama dibuka, HomeFragment menjadi halaman default
        if (savedInstanceState == null) {
            loadFragment(HomeFragment(), "Semua")
        }

        // Menampilkan semua kategori melalui HomeFragment
        binding.btnChipSemua.setOnClickListener {
            loadFragment(HomeFragment(), "Semua")
            updateChipStyles(binding.btnChipSemua)
        }

        // Menampilkan fragment khusus kategori Kuliner
        binding.btnChipKuliner.setOnClickListener {
            loadFragment(KulinerFragment(), "Kuliner")
            updateChipStyles(binding.btnChipKuliner)
        }

        // Menampilkan fragment khusus kategori Kriya
        binding.btnChipKriya.setOnClickListener {
            loadFragment(KriyaFragment(), "Kriya")
            updateChipStyles(binding.btnChipKriya)
        }

        // Menampilkan fragment khusus kategori Fashion
        binding.btnChipFashion.setOnClickListener {
            loadFragment(FashionFragment(), "Fashion")
            updateChipStyles(binding.btnChipFashion)
        }

        // Menampilkan dialog konfirmasi ketika tombol logout ditekan
        binding.btnLogout.setOnClickListener {
            showExitDialog()
        }
    }

    // Fungsi untuk mengganti fragment yang tampil di fragmentContainer
    private fun loadFragment(fragment: Fragment, category: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    // Mengubah warna tombol kategori untuk menunjukkan kategori yang sedang dipilih
    private fun updateChipStyles(selectedButton: Button) {

        val chips = listOf(
            binding.btnChipSemua,
            binding.btnChipKuliner,
            binding.btnChipKriya,
            binding.btnChipFashion
        )

        for (chip in chips) {
            if (chip == selectedButton) {
                // Tombol yang aktif menggunakan warna oranye dan teks putih
                chip.setBackgroundColor(Color.parseColor("#C94E12"))
                chip.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                // Tombol yang tidak aktif dikembalikan ke warna awal
                chip.setBackgroundColor(Color.parseColor("#F2E6DC"))
                chip.setTextColor(Color.parseColor("#3E2B2F"))
            }
        }
    }

    // Fungsi untuk menampilkan dialog konfirmasi logout
    private fun showExitDialog() {

        AlertDialog.Builder(this@MainActivity)
            .setTitle("Konfirmasi Keluar")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi Ekrafku?")
            .setPositiveButton("Ya") { _, _ ->

                // Menghapus SharedPreferences sehingga status isLogin kembali hilang
                val sharedPref = getSharedPreferences(
                    "user_pref",
                    MODE_PRIVATE
                )

                sharedPref.edit()
                    .clear()
                    .apply()

                // Setelah logout, pengguna diarahkan kembali ke LoginActivity
                val intent = Intent(
                    this@MainActivity,
                    LoginActivity::class.java
                )

                startActivity(intent)
                finish()
            }
            .setNegativeButton("Batal") { dialog, _ ->

                dialog.dismiss()

                // Snackbar memberikan informasi bahwa proses logout dibatalkan
                Snackbar.make(
                    binding.root,
                    "Batal keluar dari aplikasi",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            .create()
            .show()
    }
}