package com.riquest.watchaw.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riquest.watchaw.R
import com.riquest.watchaw.sign.signin.SignInActivity
import com.riquest.watchaw.utils.Preferences
import kotlinx.android.synthetic.main.activity_onboarding_one.*

class OnboardingOneActivity : AppCompatActivity() {

    lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)
        preference = Preferences(this)
        if(preference.getValues("onboarding").equals("1")){

            finishAffinity()
            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        btn_daftar.setOnClickListener {
            preference.setValues("onboarding","1")
            finishAffinity()
            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        btn_nextpresale.setOnClickListener{
            var intent = Intent(this@OnboardingOneActivity,OnboardingTwoActivity::class.java)
            startActivity(intent)
        }
    }
}