package com.anurag.shorturl.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anurag.shorturl.databinding.ActivityMainBinding
import com.anurag.shorturl.model.UrlRequest
import com.anurag.shorturl.model.UrlResponse
import com.anurag.shorturl.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val api = ApiClient.getInstance().getApiService()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnGo.setOnClickListener {
            val originalUrl = binding.url.text.toString().trim()
            if (originalUrl.isNotEmpty()) {
                shortenUrl(originalUrl)
            }
            else{
                binding.url.error = "Enter a URL"
                return@setOnClickListener
            }

        }
    }

    private fun shortenUrl(originalUrl: String) {
        val request = UrlRequest(url = originalUrl)

        api.sendUrl(request).enqueue(object : Callback<UrlResponse> {
            override fun onResponse(
                call: Call<UrlResponse>,
                response: Response<UrlResponse>
            ) {
                if (response.isSuccessful) {
                    val shortId = response.body()?.short_url ?: ""

                    val intent = Intent(this@MainActivity, ResultActivity::class.java)
                    intent.putExtra("short_id", shortId)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@MainActivity, "Error shortening URL", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<UrlResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}


