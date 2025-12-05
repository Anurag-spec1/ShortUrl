package com.anurag.shorturl.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
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

        startEntranceAnimations()

        animateParticles()

        binding.btnGo.setOnClickListener {
            val originalUrl = binding.url.text.toString().trim()
            if (originalUrl.isNotEmpty()) {
                animateButtonClick()
                shortenUrl(originalUrl)
            } else {
                binding.url.error = "Enter a URL"
                animateInputError()
                return@setOnClickListener
            }
        }

        binding.url.setOnFocusChangeListener { _, hasFocus ->
            animateInputFocus(hasFocus)
        }
    }

    private fun startEntranceAnimations() {
        binding.appIcon?.let { icon ->
            icon.scaleX = 0f
            icon.scaleY = 0f
            icon.alpha = 0f
            icon.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(600)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .withEndAction { animateIconPulse() }
                .start()
        }

        binding.titleText?.let { title ->
            title.translationY = -100f
            title.alpha = 0f
            title.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(700)
                .setStartDelay(200)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }

        binding.subtitleText?.let { subtitle ->
            subtitle.alpha = 0f
            subtitle.animate()
                .alpha(0.9f)
                .setDuration(600)
                .setStartDelay(400)
                .start()
        }

        binding.url.translationX = -300f
        binding.url.alpha = 0f
        binding.url.animate()
            .translationX(0f)
            .alpha(1f)
            .setDuration(700)
            .setStartDelay(500)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()

        binding.btnGo.translationY = 200f
        binding.btnGo.alpha = 0f
        binding.btnGo.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(700)
            .setStartDelay(600)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()
    }

    private fun animateIconPulse() {
        binding.appIcon?.let { icon ->
            val scaleX = ObjectAnimator.ofFloat(icon, "scaleX", 1f, 1.1f, 1f)
            val scaleY = ObjectAnimator.ofFloat(icon, "scaleY", 1f, 1.1f, 1f)

            scaleX.duration = 2000
            scaleY.duration = 2000
            scaleX.repeatCount = ValueAnimator.INFINITE
            scaleY.repeatCount = ValueAnimator.INFINITE
            scaleX.interpolator = AccelerateDecelerateInterpolator()
            scaleY.interpolator = AccelerateDecelerateInterpolator()

            scaleX.start()
            scaleY.start()
        }
    }

    private fun animateParticles() {
        binding.particle1?.let { p1 ->
            val p1TransY = ObjectAnimator.ofFloat(p1, "translationY", 0f, -50f, 0f)
            val p1TransX = ObjectAnimator.ofFloat(p1, "translationX", 0f, 30f, 0f)
            val p1Rotation = ObjectAnimator.ofFloat(p1, "rotation", 0f, 360f)

            p1TransY.duration = 4000
            p1TransX.duration = 3000
            p1Rotation.duration = 8000

            p1TransY.repeatCount = ValueAnimator.INFINITE
            p1TransX.repeatCount = ValueAnimator.INFINITE
            p1Rotation.repeatCount = ValueAnimator.INFINITE

            p1TransY.interpolator = AccelerateDecelerateInterpolator()
            p1TransX.interpolator = AccelerateDecelerateInterpolator()

            p1TransY.start()
            p1TransX.start()
            p1Rotation.start()
        }

        binding.particle2?.let { p2 ->
            val p2TransY = ObjectAnimator.ofFloat(p2, "translationY", 0f, 60f, 0f)
            val p2TransX = ObjectAnimator.ofFloat(p2, "translationX", 0f, -40f, 0f)
            val p2Rotation = ObjectAnimator.ofFloat(p2, "rotation", 0f, -360f)

            p2TransY.duration = 5000
            p2TransX.duration = 4000
            p2Rotation.duration = 10000

            p2TransY.repeatCount = ValueAnimator.INFINITE
            p2TransX.repeatCount = ValueAnimator.INFINITE
            p2Rotation.repeatCount = ValueAnimator.INFINITE

            p2TransY.interpolator = AccelerateDecelerateInterpolator()
            p2TransX.interpolator = AccelerateDecelerateInterpolator()

            p2TransY.start()
            p2TransX.start()
            p2Rotation.start()
        }
    }

    private fun animateInputFocus(hasFocus: Boolean) {
        if (hasFocus) {
            binding.url.animate()
                .scaleX(1.02f)
                .scaleY(1.02f)
                .setDuration(200)
                .start()

            binding.urlIcon?.let { icon ->
                icon.animate()
                    .alpha(1f)
                    .rotation(360f)
                    .setDuration(400)
                    .start()
            }
        } else {
            binding.url.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(200)
                .start()

            binding.urlIcon?.let { icon ->
                icon.animate()
                    .alpha(0.6f)
                    .setDuration(200)
                    .start()
            }
        }
    }

    private fun animateButtonClick() {
        val scaleXDown = ObjectAnimator.ofFloat(binding.btnGo, "scaleX", 1f, 0.95f)
        val scaleYDown = ObjectAnimator.ofFloat(binding.btnGo, "scaleY", 1f, 0.95f)
        scaleXDown.duration = 100
        scaleYDown.duration = 100

        val scaleXUp = ObjectAnimator.ofFloat(binding.btnGo, "scaleX", 0.95f, 1f)
        val scaleYUp = ObjectAnimator.ofFloat(binding.btnGo, "scaleY", 0.95f, 1f)
        scaleXUp.duration = 100
        scaleYUp.duration = 100

        val animatorSet = AnimatorSet()
        animatorSet.play(scaleXDown).with(scaleYDown)
        animatorSet.play(scaleXUp).with(scaleYUp).after(scaleXDown)
        animatorSet.start()

        binding.appIcon?.let { icon ->
            val iconRotate = ObjectAnimator.ofFloat(icon, "rotation", 0f, 360f)
            iconRotate.duration = 600
            iconRotate.start()
        }
    }

    private fun animateInputError() {
        val shake = ObjectAnimator.ofFloat(
            binding.url, "translationX",
            0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f
        )
        shake.duration = 500
        shake.start()
    }

    private fun shortenUrl(originalUrl: String) {
        binding.btnGo.text = "Shortening..."
        binding.btnGo.isEnabled = false

        val request = UrlRequest(url = originalUrl)

        api.sendUrl(request).enqueue(object : Callback<UrlResponse> {
            override fun onResponse(
                call: Call<UrlResponse>,
                response: Response<UrlResponse>
            ) {
                if (response.isSuccessful) {
                    val shortId = response.body()?.short_url ?: ""

                    animateSuccess()

                    binding.btnGo.postDelayed({
                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        intent.putExtra("short_id", shortId)
                        startActivity(intent)

                        binding.btnGo.text = "Shorten URL"
                        binding.btnGo.isEnabled = true
                    }, 800)
                } else {
                    binding.btnGo.text = "Shorten URL"
                    binding.btnGo.isEnabled = true
                    animateError()
                    Toast.makeText(this@MainActivity, "Error shortening URL", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UrlResponse>, t: Throwable) {
                binding.btnGo.text = "Shorten URL"
                binding.btnGo.isEnabled = true
                animateError()
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun animateSuccess() {
        binding.btnGo.text = "✓ Success!"

        val scaleX = ObjectAnimator.ofFloat(binding.btnGo, "scaleX", 1f, 1.1f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.btnGo, "scaleY", 1f, 1.1f, 1f)

        val set = AnimatorSet()
        set.playTogether(scaleX, scaleY)
        set.duration = 400
        set.start()

        binding.appIcon?.let { icon ->
            val iconScale = ObjectAnimator.ofFloat(icon, "scaleX", 1f, 1.3f, 1f)
            val iconScaleY = ObjectAnimator.ofFloat(icon, "scaleY", 1f, 1.3f, 1f)

            val iconSet = AnimatorSet()
            iconSet.playTogether(iconScale, iconScaleY)
            iconSet.duration = 500
            iconSet.start()
        }
    }

    private fun animateError() {
        val shake = ObjectAnimator.ofFloat(
            binding.btnGo, "translationX",
            0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f
        )
        shake.duration = 500
        shake.start()
    }

    override fun onResume() {
        super.onResume()
        binding.btnGo.text = "Shorten URL"
        binding.btnGo.isEnabled = true
    }
}