package com.anurag.shorturl.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anurag.shorturl.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private var finalShortUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shortId = intent.getStringExtra("short_id") ?: ""
        finalShortUrl = "https://urlshortnergolang.onrender.com/redirect/$shortId"

        binding.shortUrlText.text = finalShortUrl

        startEntranceAnimations()

        animateParticles()

        binding.urlCardContainer?.setOnClickListener {
            animateCardClick()
            openUrlInBrowser()
        }

        binding.shortUrlText.setOnClickListener {
            animateCardClick()
            openUrlInBrowser()
        }

        binding.btnCopy?.setOnClickListener {
            animateCopyButton()
            copyToClipboard()
        }

        binding.btnShare?.setOnClickListener {
            animateShareButton()
            shareUrl()
        }

        binding.btnBack?.setOnClickListener {
            animateBackButton()
            finish()
        }
    }

    private fun startEntranceAnimations() {
        binding.successIconContainer?.apply {
            scaleX = 0f
            scaleY = 0f
            alpha = 0f
            animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(600)
                .setInterpolator(BounceInterpolator())
                .withEndAction {
                    animateSuccessIconPulse()
                }
                .start()
        }

        binding.successIcon?.apply {
            rotation = -180f
            animate()
                .rotation(0f)
                .setDuration(600)
                .setStartDelay(200)
                .setInterpolator(OvershootInterpolator())
                .start()
        }

        binding.successTitle?.apply {
            translationY = -100f
            alpha = 0f
            animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(700)
                .setStartDelay(300)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }

        binding.successSubtitle?.apply {
            alpha = 0f
            animate()
                .alpha(0.9f)
                .setDuration(600)
                .setStartDelay(500)
                .start()
        }

        binding.urlCardContainer?.apply {
            translationY = 200f
            alpha = 0f
            animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(700)
                .setStartDelay(600)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }

        binding.btnCopy?.apply {
            translationX = -200f
            alpha = 0f
            animate()
                .translationX(0f)
                .alpha(1f)
                .setDuration(600)
                .setStartDelay(800)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }

        binding.btnShare?.apply {
            translationX = 200f
            alpha = 0f
            animate()
                .translationX(0f)
                .alpha(1f)
                .setDuration(600)
                .setStartDelay(800)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }

        binding.btnBack?.apply {
            alpha = 0f
            animate()
                .alpha(1f)
                .setDuration(600)
                .setStartDelay(1000)
                .start()
        }
    }

    private fun animateSuccessIconPulse() {
        binding.successIconContainer?.let { container ->
            val scaleX = ObjectAnimator.ofFloat(container, "scaleX", 1f, 1.05f, 1f)
            val scaleY = ObjectAnimator.ofFloat(container, "scaleY", 1f, 1.05f, 1f)

            scaleX.duration = 1500
            scaleY.duration = 1500
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
            val p1TransY = ObjectAnimator.ofFloat(p1, "translationY", 0f, -40f, 0f)
            val p1TransX = ObjectAnimator.ofFloat(p1, "translationX", 0f, 40f, 0f)
            val p1Rotation = ObjectAnimator.ofFloat(p1, "rotation", 0f, 360f)

            p1TransY.duration = 5000
            p1TransX.duration = 4000
            p1Rotation.duration = 10000

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
            val p2TransY = ObjectAnimator.ofFloat(p2, "translationY", 0f, 50f, 0f)
            val p2TransX = ObjectAnimator.ofFloat(p2, "translationX", 0f, -30f, 0f)
            val p2Rotation = ObjectAnimator.ofFloat(p2, "rotation", 0f, -360f)

            p2TransY.duration = 6000
            p2TransX.duration = 5000
            p2Rotation.duration = 12000

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

    private fun animateCardClick() {
        binding.urlCardContainer?.let { card ->
            val scaleX = ObjectAnimator.ofFloat(card, "scaleX", 1f, 0.97f, 1f)
            val scaleY = ObjectAnimator.ofFloat(card, "scaleY", 1f, 0.97f, 1f)

            val set = AnimatorSet()
            set.playTogether(scaleX, scaleY)
            set.duration = 200
            set.start()
        }
    }

    private fun animateCopyButton() {
        binding.btnCopy?.let { btn ->
            val scaleX = ObjectAnimator.ofFloat(btn, "scaleX", 1f, 0.9f, 1.1f, 1f)
            val scaleY = ObjectAnimator.ofFloat(btn, "scaleY", 1f, 0.9f, 1.1f, 1f)

            val set = AnimatorSet()
            set.playTogether(scaleX, scaleY)
            set.duration = 400
            set.interpolator = AccelerateDecelerateInterpolator()
            set.start()
        }

        binding.copyIcon?.let { icon ->
            val rotation = ObjectAnimator.ofFloat(icon, "rotation", 0f, 360f)
            rotation.duration = 400
            rotation.start()
        }

        binding.copyText?.let { text ->
            val originalText = text.text
            text.text = "Copied!"
            text.postDelayed({
                text.text = originalText
            }, 2000)
        }
    }

    private fun animateShareButton() {
        binding.btnShare?.let { btn ->
            val scaleX = ObjectAnimator.ofFloat(btn, "scaleX", 1f, 1.1f, 1f)
            val scaleY = ObjectAnimator.ofFloat(btn, "scaleY", 1f, 1.1f, 1f)

            val set = AnimatorSet()
            set.playTogether(scaleX, scaleY)
            set.duration = 300
            set.start()
        }
    }

    private fun animateBackButton() {
        binding.btnBack?.let { btn ->
            val alpha = ObjectAnimator.ofFloat(btn, "alpha", 1f, 0f)
            alpha.duration = 300
            alpha.start()
        }
    }

    private fun openUrlInBrowser() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(finalShortUrl))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Unable to open URL", Toast.LENGTH_SHORT).show()
        }
    }

    private fun copyToClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Short URL", finalShortUrl)
        clipboard.setPrimaryClip(clip)

        Toast.makeText(this, "URL copied to clipboard!", Toast.LENGTH_SHORT).show()
    }

    private fun shareUrl() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Check out this link: $finalShortUrl")
            putExtra(Intent.EXTRA_SUBJECT, "Shortened URL")
        }

        try {
            startActivity(Intent.createChooser(shareIntent, "Share URL via"))
        } catch (e: Exception) {
            Toast.makeText(this, "Unable to share", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        animateBackButton()
        super.onBackPressed()
    }
}