package com.example.projectmobile_aima3tib

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class TutorialActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var layoutIndicator: LinearLayout
    private lateinit var btnNext: Button
    private lateinit var btnSkip: TextView

    private val tutorialTitles = listOf(
        "Jelajahi Karya Lokal",
        "Temukan Produk Unik",
        "Dukung Pelaku Usaha"
    )

    private val tutorialDescriptions = listOf(
        "Kenali berbagai produk kreatif lokal yang memiliki cerita dan keunikan.",
        "Jelajahi produk dari kategori Kuliner, Kriya, dan Fashion.",
        "Lihat informasi produk dan hubungi pelaku usaha secara langsung."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        viewPager = findViewById(R.id.viewPagerTutorial)
        layoutIndicator = findViewById(R.id.layoutIndicator)
        btnNext = findViewById(R.id.btnNext)
        btnSkip = findViewById(R.id.btnSkip)

        viewPager.adapter = TutorialAdapter(
            tutorialTitles,
            tutorialDescriptions,
            images = listOf(
                R.drawable.tutor1,
                R.drawable.tutor2,
                R.drawable.tutor3
            )
        )

        setupIndicators()
        setCurrentIndicator(0)

        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setCurrentIndicator(position)

                    btnNext.text = if (
                        position == tutorialTitles.lastIndex
                    ) {
                        "Mulai"
                    } else {
                        "Lanjut"
                    }
                }
            }
        )

        btnNext.setOnClickListener {
            if (viewPager.currentItem < tutorialTitles.lastIndex) {
                viewPager.currentItem += 1
            } else {
                openNextScreen()
            }
        }

        btnSkip.setOnClickListener {
            openNextScreen()
        }
    }

    private fun setupIndicators() {
        val indicators = Array(tutorialTitles.size) {
            TextView(this)
        }

        val layoutParams = LinearLayout.LayoutParams(
            24,
            8
        )

        layoutParams.setMargins(6, 0, 6, 0)

        indicators.forEach {
            it.background = indicatorDrawable("#D9C5B8")
            it.layoutParams = layoutParams
            layoutIndicator.addView(it)
        }
    }

    private fun setCurrentIndicator(index: Int) {
        for (i in 0 until layoutIndicator.childCount) {
            val indicator = layoutIndicator.getChildAt(i)

            indicator.background = indicatorDrawable(
                if (i == index) "#B87555" else "#D9C5B8"
            )
        }
    }

    private fun indicatorDrawable(color: String): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.parseColor(color))
            cornerRadius = 20f
        }
    }

    private fun openNextScreen() {
        getSharedPreferences("user_pref", MODE_PRIVATE)
            .edit()
            .putBoolean("tutorialCompleted", true)
            .apply()

        startActivity(
            Intent(this, LoginActivity::class.java)
        )

        finish()
    }
}
