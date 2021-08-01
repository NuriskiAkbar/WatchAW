package com.riquest.watchaw.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.riquest.watchaw.R
import com.riquest.watchaw.home.dashboard.DashboardFragment
import com.riquest.watchaw.home.setting.SettingFragment
import com.riquest.watchaw.home.tiket.TicketFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentSetting = SettingFragment()
        val fragmentTicket = TicketFragment()
        val fragmentHome = DashboardFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener{
            setFragment(fragmentHome)

            changeIcon(iv_menu1, R.drawable.iconhomeselected)
            changeIcon(iv_menu2, R.drawable.iconticket)
            changeIcon(iv_menu3, R.drawable.iconprofile)
        }

        iv_menu2.setOnClickListener{
            setFragment(fragmentTicket)

            changeIcon(iv_menu1, R.drawable.iconhome)
            changeIcon(iv_menu2, R.drawable.iconticketselected)
            changeIcon(iv_menu3, R.drawable.iconprofile)
        }

        iv_menu3.setOnClickListener{
            setFragment(fragmentSetting)

            changeIcon(iv_menu1, R.drawable.iconhome)
            changeIcon(iv_menu2, R.drawable.iconticket)
            changeIcon(iv_menu3, R.drawable.iconprofileselected)
        }
    }

    private fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int : Int){
        imageView.setImageResource(int)
    }
}
