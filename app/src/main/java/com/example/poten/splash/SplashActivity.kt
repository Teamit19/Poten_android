package com.example.poten.splash

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.poten.Login.LoginActivity
import com.example.poten.R
import com.example.poten.databinding.ActivityClubMyPageBinding

class SplashActivity : AppCompatActivity() {



    private lateinit var view1_red : ConstraintLayout
    private lateinit var view1_logo : ImageView

    private lateinit var view2_white : ConstraintLayout
    private lateinit var view2_logo : ImageView

    private lateinit var fadeIn :  ObjectAnimator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        view1_red = findViewById(R.id.view1_red)
        view1_logo = findViewById(R.id.view1_logo)

        view2_white = findViewById(R.id.view2_white)
        view2_logo = findViewById(R.id.view2_logo)


        val fadeOut_view = ObjectAnimator.ofFloat(view1_red, "alpha", 1f, 0f) // fadeout
        fadeOut_view.duration = 2000

        Handler().postDelayed({
            fadeOut_view.start()
        },DURATION)

        var fadeInView2 = ObjectAnimator.ofFloat(view2_white, "alpha",  0f, 1f) // fadein
        fadeInView2.duration = 1000

        fadeIn = ObjectAnimator.ofFloat(view2_logo, "alpha",  0f, 1f) // fadein
        fadeIn.duration = 2000


        fadeOut_view.addListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                Log.i("SPLASH", "onAnimationEnd")
                fadeInView2.start()
                fadeIn.start()
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationRepeat(p0: Animator?) {

            }

        })

        fadeInView2.addListener(object  : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {
                view1_red.visibility = View.GONE
                view2_white.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(p0: Animator?) {

            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationRepeat(p0: Animator?) {

            }

        })

        fadeIn.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationRepeat(p0: Animator?) {

            }

        })

    }
    companion object {
        private const val DURATION : Long = 1500
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}