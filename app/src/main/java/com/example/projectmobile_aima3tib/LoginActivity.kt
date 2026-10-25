package com.example.projectmobile_aima3tib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmobile_aima3tib.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
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
        binding.btnLogin.setOnClickListener {

            val email = binding.edtUsername.text.toString()
            val pass = binding.edtPassword.text.toString()
            val user = email

            Log.d("Username", email)
            Log.d("Password", pass)

            Toast.makeText(
                this,
                "Login berhasil",
                Toast.LENGTH_SHORT
            ).show()

            if (user.isNotEmpty() && user == pass) {
                val editor = sharedPref.edit()

                // Jika login berhasil, status isLogin disimpan menjadi true
                // agar saat aplikasi dibuka kembali pengguna bisa langsung masuk ke MainActivity
                editor.putBoolean("isLogin", true)

                // Menyimpan username pengguna ke SharedPreferences
                editor.putString("username", user)
                editor.apply()
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
    }
}
