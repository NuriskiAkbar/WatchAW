package com.riquest.watchaw.home.tiket

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.riquest.watchaw.R
import com.riquest.watchaw.model.Checkout
import com.riquest.watchaw.model.Film
import kotlinx.android.synthetic.main.activity_ticket.*

class TicketActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        var data = intent.getParcelableExtra<Film>("data")

        tv_title.text = data?.judul
        tv_genre.text = data?.judul
        tv_rate.text = data?.judul

        Glide.with(this)
            .load(data?.poster)
            .into(iv_poster)

        rc_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C3", ""))
        dataList.add(Checkout("C4", ""))

        val builder = AlertDialog.Builder(this)
        iv_barcode.setOnClickListener {
            builder.setView(R.layout.popup_dialog)
            builder.setNegativeButton("Ketuk sembarang untuk menutup",{ dialogInterface: DialogInterface, i: Int ->})
            builder.show()
        }


        rc_checkout.adapter = TicketAdapter(dataList){

        }
    }
}