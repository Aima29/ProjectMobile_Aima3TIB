package com.example.projectmobile_aima3tib

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// SplashScreen adalah halaman pertama yang dijalankan saat aplikasi dibuka
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Menghubungkan SplashScreen dengan layout activity_splash_screen.xml
        setContentView(R.layout.activity_splash_screen)

        // Menyesuaikan tampilan agar tidak tertutup status bar dan navigation bar
        val mainView = findViewById<android.view.View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                )

                v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
                )

                insets
            }
        }

        // Tampilkan splash screen selama 2 detik sebelum pindah halaman
        Handler(Looper.getMainLooper()).postDelayed({
            if (isFinishing || isDestroyed) return@postDelayed

            // Mengambil status login dari SharedPreferences
            val sharedPref = getSharedPreferences(
                "user_pref",
                MODE_PRIVATE
            )

            val isLogin = sharedPref.getBoolean(
                "isLogin",
                false
            )

            val intent = if (isLogin) {
                Intent(this@SplashScreen, BaseActivity::class.java)
            } else {
                Intent(this@SplashScreen, LoginActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 2000)
    }
}
