package com.riquest.watchaw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.riquest.watchaw.home.dashboard.PlaysAdapter
import com.riquest.watchaw.model.Film
import com.riquest.watchaw.model.Plays
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Film>("data")
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
                .child(data?.judul.toString())
                .child("play")


        tv_judul.text = data?.judul
        tv_genre.text = data?.genre
        tv_desc.text = data?.desc
        tv_rating.text = data?.rating

        Glide.with(this)
                .load(data?.poster)
                .into(iv_poster)

        btn_pilihbangku.setOnClickListener{
            var intent = Intent(this@DetailActivity,
                    PilihBangkuActivity::class.java)
                    .putExtra("data", data)
            startActivity(intent)
        }

        rv_cast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                dataList.clear()
                for(getdataSnapshot in snapshot.children){
                    val film = getdataSnapshot.getValue(Plays::class.java)
                    dataList.add(film!!)
                }

                rv_cast.adapter = PlaysAdapter(dataList){

                }
            }

        })
    }
}

