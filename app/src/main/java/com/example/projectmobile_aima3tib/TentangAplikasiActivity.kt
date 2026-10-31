package com.example.projectmobile_aima3tib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmobile_aima3tib.databinding.ActivityTentangAplikasiBinding

class TentangAplikasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTentangAplikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTentangAplikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}