package com.jetpack.pagingdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paging_room.setOnClickListener { goToRoomExample() }
        paging_net.setOnClickListener { goToNetExample() }
//        paging_room.setOnClickListener(object :View.OnClickListener {
//            override fun onClick(p0: View?) {
//
//            }
//        })

    }

    private fun goToNetExample() {
        startActivity(Intent(this, NetListActivity::class.java))
    }

    private fun goToRoomExample() {
        startActivity(Intent(this, RoomListActivity::class.java))
    }
}
