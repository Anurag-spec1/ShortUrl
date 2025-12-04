package com.anurag.shorturl.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anurag.shorturl.R
import com.anurag.shorturl.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shortId = intent.getStringExtra("short_id") ?: ""

        val finalShortUrl = "https://urlshortnergolang.onrender.com/redirect/$shortId"

        binding.shortUrlText.text = finalShortUrl

        binding.shortUrlText.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(finalShortUrl))
            startActivity(intent)
        }
    }
}
