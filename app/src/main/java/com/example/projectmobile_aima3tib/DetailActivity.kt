
package com.example.projectmobile_aima3tib

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmobile_aima3tib.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("CATEGORY") ?: "KRIYA"
        val namaProduk = intent.getStringExtra("NAMA_PRODUK")

        // Jika nama produk dikirim, tampilkan detail produk tersebut.
        // Jika tidak, tampilkan detail kategori lama seperti semula.
        if (!namaProduk.isNullOrEmpty()) {
            tampilkanDetailProduk(namaProduk)
        } else {
            tampilkanDetailKategori(category)
        }

        binding.cardBack.setOnClickListener {
            finish()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.cardFavorite.setOnClickListener {
            isFavorite = !isFavorite

            val pesan = if (isFavorite) {
                "Ditambahkan ke favorit"
            } else {
                "Dihapus dari favorit"
            }

            Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show()
        }

        binding.btnContact.setOnClickListener {
            Toast.makeText(
                this,
                "Menghubungi pelaku usaha via WhatsApp...",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun tampilkanDetailKategori(category: String) {
        when (category) {
            "KULINER" -> {
                binding.ivHeader.setImageResource(R.drawable.kuliner)
                binding.tvDetailTitle.text = "Rasa Nusantara"
                binding.tvDetailLocation.text = "📍 Pekanbaru"
                binding.tvDetailPrice.text = "Rp35.000"
                binding.tvDetailRating.text = "★ 4,9 · 82 ulasan"
                binding.tvDetailTagline.text =
                    "Cita rasa lokal dalam setiap sajian."

                binding.tvDetailDescription.text = """
                    Rasa Nusantara menghadirkan kuliner lokal dengan cita rasa khas Indonesia yang dibuat menggunakan bahan pilihan dan resep tradisional.

                    Setiap sajian dibuat dengan mempertahankan rasa autentik sekaligus dikemas secara menarik agar dapat dinikmati oleh berbagai kalangan.

                    Bahan: Bahan lokal pilihan
                    Kategori: Kuliner
                    Proses: Diolah dengan resep tradisional
                    Keunggulan: Cita rasa autentik, berkualitas, dan mendukung usaha kuliner lokal.
                """.trimIndent()
            }

            "KRIYA" -> {
                binding.ivHeader.setImageResource(R.drawable.kriya)
                binding.tvDetailTitle.text = "Tas Rotan Lestari"
                binding.tvDetailLocation.text = "📍 Cirebon"
                binding.tvDetailPrice.text = "Rp185.000"
                binding.tvDetailRating.text = "★ 4,8 · 64 ulasan"
                binding.tvDetailTagline.text =
                    "Teranyam dari alam dan tradisi."

                binding.tvDetailDescription.text = """
                    Tas Anyaman Lestari merupakan produk kriya lokal yang dibuat secara handmade menggunakan bahan rotan pilihan. Proses pembuatannya dilakukan dengan teknik anyaman tradisional yang menghasilkan pola khas, kuat, dan memiliki nilai estetika.

                    Tas ini cocok digunakan untuk aktivitas sehari-hari maupun sebagai pelengkap gaya. Selain memiliki desain yang sederhana dan natural, produk ini juga menjadi salah satu bentuk pelestarian kerajinan lokal dan mendukung para pengrajin Indonesia.

                    Bahan: Rotan pilihan
                    Kategori: Kriya
                    Proses: Handmade / anyaman tradisional
                    Keunggulan: Ringan, kuat, unik, dan ramah lingkungan.
                """.trimIndent()
            }

            "FASHION" -> {
                binding.ivHeader.setImageResource(R.drawable.fashion)
                binding.tvDetailTitle.text = "Pesona Wastra"
                binding.tvDetailLocation.text = "📍 Pekanbaru"
                binding.tvDetailPrice.text = "Rp250.000"
                binding.tvDetailRating.text = "★ 4,8 · 51 ulasan"
                binding.tvDetailTagline.text =
                    "Tradisi yang hadir dalam gaya masa kini."

                binding.tvDetailDescription.text = """
                    Pesona Wastra merupakan produk fashion lokal yang memadukan unsur tradisional dengan desain modern.

                    Produk dibuat dengan memperhatikan detail, kenyamanan, dan karakter khas karya lokal sehingga dapat digunakan dalam berbagai kesempatan.

                    Bahan: Kain pilihan
                    Kategori: Fashion
                    Proses: Produksi dan pengerjaan lokal
                    Keunggulan: Nyaman, unik, modern, dan mengangkat identitas lokal.
                """.trimIndent()
            }

            else -> {
                binding.tvDetailTitle.text = "Produk Ekraf"
            }
        }
    }

    private fun tampilkanDetailProduk(namaProduk: String) {
        when (namaProduk) {
            "Rendang" -> {
                binding.ivHeader.setImageResource(R.drawable.rendang)
                binding.tvDetailTitle.text = "Rendang"
                binding.tvDetailLocation.text = "📍 Sumatera Barat"
                binding.tvDetailPrice.text = "Rp35.000"
                binding.tvDetailRating.text = "★ 4,9 · 82 ulasan"
                binding.tvDetailTagline.text =
                    "Cita rasa Minangkabau yang autentik."

                binding.tvDetailDescription.text = """
                    Rendang merupakan kuliner khas Sumatera Barat yang dimasak menggunakan daging sapi dan rempah-rempah pilihan.

                    Bahan: Daging sapi dan rempah pilihan
                    Kategori: Kuliner
                    Proses: Dimasak dengan bumbu tradisional
                    Keunggulan: Kaya rempah dan bercita rasa khas.
                """.trimIndent()
            }

            "Pempek" -> {
                binding.ivHeader.setImageResource(R.drawable.pempek)
                binding.tvDetailTitle.text = "Pempek"
                binding.tvDetailLocation.text = "📍 Palembang"
                binding.tvDetailPrice.text = "Rp20.000"
                binding.tvDetailRating.text = "★ 4,8 · 75 ulasan"
                binding.tvDetailTagline.text =
                    "Gurihnya olahan ikan khas Palembang."

                binding.tvDetailDescription.text = """
                    Pempek adalah makanan khas Palembang yang dibuat dari ikan dan tepung sagu, biasanya disajikan bersama kuah cuko.

                    Bahan: Ikan dan tepung sagu
                    Kategori: Kuliner
                    Proses: Diolah dan disajikan dengan kuah cuko
                    Keunggulan: Gurih dengan cita rasa khas.
                """.trimIndent()
            }

            "Gulai Ikan Patin" -> {
                binding.ivHeader.setImageResource(R.drawable.gulaipatin)
                binding.tvDetailTitle.text = "Gulai Ikan Patin"
                binding.tvDetailLocation.text = "📍 Riau"
                binding.tvDetailPrice.text = "Rp30.000"
                binding.tvDetailRating.text = "★ 4,8 · 68 ulasan"
                binding.tvDetailTagline.text =
                    "Kuliner khas Riau dengan kuah rempah."

                binding.tvDetailDescription.text = """
                    Gulai ikan patin merupakan hidangan khas Riau yang diolah menggunakan ikan patin dan bumbu gulai.

                    Bahan: Ikan patin dan bumbu gulai
                    Kategori: Kuliner
                    Proses: Dimasak dengan bumbu dan rempah
                    Keunggulan: Gurih dan kaya rempah.
                """.trimIndent()
            }

            "Anyaman Rotan" -> {
                binding.ivHeader.setImageResource(R.drawable.anyaman)
                binding.tvDetailTitle.text = "Anyaman Rotan"
                binding.tvDetailLocation.text = "📍 Indonesia"
                binding.tvDetailPrice.text = "Rp150.000"
                binding.tvDetailRating.text = "★ 4,8 · 64 ulasan"
                binding.tvDetailTagline.text =
                    "Kerajinan alami dengan sentuhan tradisi."

                binding.tvDetailDescription.text = """
                    Anyaman rotan merupakan karya kriya yang dibuat dengan teknik menganyam rotan menjadi produk yang fungsional dan bernilai estetika.

                    Bahan: Rotan
                    Kategori: Kriya
                    Proses: Anyaman tradisional
                    Keunggulan: Unik, kuat, dan memiliki nilai kerajinan.
                """.trimIndent()
            }

            "Aksesori Lokal" -> {
                binding.ivHeader.setImageResource(R.drawable.aksesoris)
                binding.tvDetailTitle.text = "Aksesori Lokal"
                binding.tvDetailLocation.text = "📍 Indonesia"
                binding.tvDetailPrice.text = "Rp50.000"
                binding.tvDetailRating.text = "★ 4,7 · 45 ulasan"
                binding.tvDetailTagline.text =
                    "Aksesori unik hasil karya pengrajin lokal."

                binding.tvDetailDescription.text = """
                    Aksesori lokal merupakan produk pelengkap gaya yang dibuat oleh pengrajin dengan desain kreatif.

                    Bahan: Menyesuaikan jenis aksesori
                    Kategori: Kriya
                    Proses: Pengerjaan oleh pengrajin lokal
                    Keunggulan: Unik dan mendukung pengrajin lokal.
                """.trimIndent()
            }

            "Ukiran Kayu" -> {
                binding.ivHeader.setImageResource(R.drawable.ukirankayu)
                binding.tvDetailTitle.text = "Ukiran Kayu"
                binding.tvDetailLocation.text = "📍 Indonesia"
                binding.tvDetailPrice.text = "Rp175.000"
                binding.tvDetailRating.text = "★ 4,8 · 52 ulasan"
                binding.tvDetailTagline.text =
                    "Seni ukir yang menghadirkan nilai budaya."

                binding.tvDetailDescription.text = """
                    Ukiran kayu merupakan karya kriya yang dibuat dengan membentuk motif pada permukaan kayu.

                    Bahan: Kayu
                    Kategori: Kriya
                    Proses: Diukir dengan teknik kerajinan
                    Keunggulan: Memiliki motif dan nilai seni.
                """.trimIndent()
            }

            "Batik Fashion" -> {
                binding.ivHeader.setImageResource(R.drawable.bajubatik)
                binding.tvDetailTitle.text = "Batik Fashion"
                binding.tvDetailLocation.text = "📍 Indonesia"
                binding.tvDetailPrice.text = "Rp250.000"
                binding.tvDetailRating.text = "★ 4,8 · 51 ulasan"
                binding.tvDetailTagline.text =
                    "Motif tradisional dalam gaya modern."

                binding.tvDetailDescription.text = """
                    Batik fashion memadukan motif batik dengan desain busana yang dapat digunakan dalam berbagai kesempatan.

                    Bahan: Kain batik
                    Kategori: Fashion
                    Proses: Produksi busana lokal
                    Keunggulan: Menampilkan identitas budaya Indonesia.
                """.trimIndent()
            }

            "Tenun Melayu" -> {
                binding.ivHeader.setImageResource(R.drawable.tenun)
                binding.tvDetailTitle.text = "Tenun Melayu"
                binding.tvDetailLocation.text = "📍 Riau"
                binding.tvDetailPrice.text = "Rp250.000"
                binding.tvDetailRating.text = "★ 4,8 · 49 ulasan"
                binding.tvDetailTagline.text =
                    "Keindahan kain tenun dengan sentuhan Melayu."

                binding.tvDetailDescription.text = """
                    Tenun Melayu merupakan produk tekstil yang menampilkan motif dan karakter budaya Melayu.

                    Bahan: Kain tenun
                    Kategori: Fashion
                    Proses: Pembuatan kain tenun
                    Keunggulan: Motif khas dan nilai budaya.
                """.trimIndent()
            }

            "Batik" -> {
                binding.ivHeader.setImageResource(R.drawable.batik)
                binding.tvDetailTitle.text = "Batik"
                binding.tvDetailLocation.text = "📍 Indonesia"
                binding.tvDetailPrice.text = "Rp180.000"
                binding.tvDetailRating.text = "★ 4,7 · 43 ulasan"
                binding.tvDetailTagline.text =
                    "Karya seni pada kain yang penuh makna."

                binding.tvDetailDescription.text = """
                    Batik merupakan karya seni pada kain yang memiliki beragam motif dan nilai budaya.

                    Bahan: Kain
                    Kategori: Fashion
                    Proses: Pembuatan motif batik
                    Keunggulan: Motif beragam dan bernilai budaya.
                """.trimIndent()
            }

            else -> {
                // Jika nama produk tidak dikenali, gunakan detail kategori lama.
                val category = intent.getStringExtra("CATEGORY") ?: "KRIYA"
                tampilkanDetailKategori(category)
            }
        }
    }
}
