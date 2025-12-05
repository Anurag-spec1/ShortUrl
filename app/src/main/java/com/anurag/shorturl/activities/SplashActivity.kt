package com.anurag.shorturl.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.anurag.shorturl.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashDuration = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemUI()

        startSplashAnimations()

        Handler(Looper.getMainLooper()).postDelayed({
            navigateToMain()
        }, splashDuration)
    }

    private fun startSplashAnimations() {
        animateParticles()

        animateLogo()

        animateAppName()

        animateTagline()

        animateLoadingDots()

        animateCreatorSection()
    }

    private fun animateParticles() {
        binding.particle1?.let { p1 ->
            val translateY = ObjectAnimator.ofFloat(p1, "translationY", 0f, -80f, 0f)
            val translateX = ObjectAnimator.ofFloat(p1, "translationX", 0f, -50f, 0f)
            val rotation = ObjectAnimator.ofFloat(p1, "rotation", 0f, 360f)
            val scale = ObjectAnimator.ofFloat(p1, "scaleX", 1f, 1.2f, 1f)
            val scaleY = ObjectAnimator.ofFloat(p1, "scaleY", 1f, 1.2f, 1f)

            translateY.duration = 4000
            translateX.duration = 3000
            rotation.duration = 8000
            scale.duration = 4000
            scaleY.duration = 4000

            translateY.repeatCount = ValueAnimator.INFINITE
            translateX.repeatCount = ValueAnimator.INFINITE
            rotation.repeatCount = ValueAnimator.INFINITE
            scale.repeatCount = ValueAnimator.INFINITE
            scaleY.repeatCount = ValueAnimator.INFINITE

            translateY.interpolator = AccelerateDecelerateInterpolator()
            translateX.interpolator = AccelerateDecelerateInterpolator()

            translateY.start()
            translateX.start()
            rotation.start()
            scale.start()
            scaleY.start()
        }

        binding.particle2?.let { p2 ->
            val translateY = ObjectAnimator.ofFloat(p2, "translationY", 0f, 60f, 0f)
            val translateX = ObjectAnimator.ofFloat(p2, "translationX", 0f, 40f, 0f)
            val rotation = ObjectAnimator.ofFloat(p2, "rotation", 0f, -360f)

            translateY.duration = 5000
            translateX.duration = 4000
            rotation.duration = 10000

            translateY.repeatCount = ValueAnimator.INFINITE
            translateX.repeatCount = ValueAnimator.INFINITE
            rotation.repeatCount = ValueAnimator.INFINITE

            translateY.interpolator = AccelerateDecelerateInterpolator()
            translateX.interpolator = AccelerateDecelerateInterpolator()

            translateY.start()
            translateX.start()
            rotation.start()
        }

        binding.particle3?.let { p3 ->
            val translateY = ObjectAnimator.ofFloat(p3, "translationY", 0f, -40f, 0f)
            val scale = ObjectAnimator.ofFloat(p3, "scaleX", 1f, 1.3f, 1f)
            val scaleY = ObjectAnimator.ofFloat(p3, "scaleY", 1f, 1.3f, 1f)
            val rotation = ObjectAnimator.ofFloat(p3, "rotation", 0f, 180f, 360f)

            translateY.duration = 3500
            scale.duration = 3500
            scaleY.duration = 3500
            rotation.duration = 7000

            translateY.repeatCount = ValueAnimator.INFINITE
            scale.repeatCount = ValueAnimator.INFINITE
            scaleY.repeatCount = ValueAnimator.INFINITE
            rotation.repeatCount = ValueAnimator.INFINITE

            translateY.interpolator = AccelerateDecelerateInterpolator()

            translateY.start()
            scale.start()
            scaleY.start()
            rotation.start()
        }
    }

    private fun animateLogo() {
        binding.logoContainer?.apply {
            scaleX = 0f
            scaleY = 0f
            alpha = 0f
            rotation = -180f

            animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .rotation(0f)
                .setDuration(1000)
                .setStartDelay(200)
                .setInterpolator(BounceInterpolator())
                .withEndAction {
                    startLogoPulse()
                }
                .start()
        }

        binding.logoIcon?.apply {
            rotation = 0f
            postDelayed({
                animate()
                    .rotation(360f)
                    .setDuration(800)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .start()
            }, 1200)
        }
    }

    private fun startLogoPulse() {
        binding.logoContainer?.let { logo ->
            val scaleX = ObjectAnimator.ofFloat(logo, "scaleX", 1f, 1.08f, 1f)
            val scaleY = ObjectAnimator.ofFloat(logo, "scaleY", 1f, 1.08f, 1f)

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

    private fun animateAppName() {
        binding.appName?.apply {
            translationY = 100f
            alpha = 0f
            scaleX = 0.8f
            scaleY = 0.8f

            animate()
                .translationY(0f)
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(800)
                .setStartDelay(800)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    private fun animateTagline() {
        binding.tagline?.apply {
            alpha = 0f
            translationY = 30f

            animate()
                .alpha(0.9f)
                .translationY(0f)
                .setDuration(600)
                .setStartDelay(1200)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    private fun animateLoadingDots() {
        val dots = listOf(binding.dot1, binding.dot2, binding.dot3)

        dots.forEachIndexed { index, dot ->
            dot?.let {
                it.alpha = 0.3f
                it.scaleX = 0.8f
                it.scaleY = 0.8f

                val alpha = ObjectAnimator.ofFloat(it, "alpha", 0.3f, 1f, 0.3f)
                val scaleX = ObjectAnimator.ofFloat(it, "scaleX", 0.8f, 1.2f, 0.8f)
                val scaleY = ObjectAnimator.ofFloat(it, "scaleY", 0.8f, 1.2f, 0.8f)

                alpha.duration = 1200
                scaleX.duration = 1200
                scaleY.duration = 1200

                alpha.startDelay = (index * 200L) + 1500L
                scaleX.startDelay = (index * 200L) + 1500L
                scaleY.startDelay = (index * 200L) + 1500L

                alpha.repeatCount = ValueAnimator.INFINITE
                scaleX.repeatCount = ValueAnimator.INFINITE
                scaleY.repeatCount = ValueAnimator.INFINITE

                alpha.interpolator = AccelerateDecelerateInterpolator()
                scaleX.interpolator = AccelerateDecelerateInterpolator()
                scaleY.interpolator = AccelerateDecelerateInterpolator()

                alpha.start()
                scaleX.start()
                scaleY.start()
            }
        }
    }

    private fun animateCreatorSection() {
        binding.createdByLabel?.parent?.let { parent ->
            if (parent is View) {
                parent.alpha = 0f
                parent.translationY = 50f

                parent.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(800)
                    .setStartDelay(1800)
                    .setInterpolator(DecelerateInterpolator())
                    .start()
            }
        }

        binding.createdByLabel?.apply {
            alpha = 0f
            animate()
                .alpha(1f)
                .setDuration(600)
                .setStartDelay(2000)
                .start()
        }

        binding.creatorName?.apply {
            alpha = 0f
            scaleX = 0.5f
            scaleY = 0.5f
            translationY = 30f

            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .translationY(0f)
                .setDuration(700)
                .setStartDelay(2200)
                .setInterpolator(AnticipateOvershootInterpolator())
                .withEndAction {
                    startNameGlow()
                }
                .start()
        }

        binding.versionText?.apply {
            alpha = 0f
            animate()
                .alpha(0.7f)
                .setDuration(500)
                .setStartDelay(2600)
                .start()
        }
    }

    private fun startNameGlow() {
        binding.creatorName?.let { name ->
            val scaleX = ObjectAnimator.ofFloat(name, "scaleX", 1f, 1.05f, 1f)
            val scaleY = ObjectAnimator.ofFloat(name, "scaleY", 1f, 1.05f, 1f)

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

    private fun navigateToMain() {
        binding.root.animate()
            .alpha(0f)
            .setDuration(400)
            .withEndAction {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            .start()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )
    }
}