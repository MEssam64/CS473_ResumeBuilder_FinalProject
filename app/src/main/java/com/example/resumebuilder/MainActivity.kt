package com.example.resumebuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.room.Room
import com.example.resumebuilder.models.CVDataBase
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MyViewAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tLayout, viewPager) {tab, position ->
            when(position) {
                0 -> {
                    tab.text = "About me"
                }
                1 -> {
                    tab.text = "Work Experience"
                }
                2 -> {
                    tab.text = "Education"
                }
                3 -> {
                    tab.text = "External Links"
                }
//                4 -> {
//                    tab.text = "introduce"
//                }
            }
        }.attach()

        val db = Room.databaseBuilder(
            applicationContext,
            CVDataBase::class.java,
            "CVDataBase"
        ).allowMainThreadQueries().build()

        val userDAO = db.getDao()
        var ss = userDAO.getAllUsers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.video_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.introduce-> {
                val intent = Intent(this, IntroduceActivity::class.java)
                startActivity(intent)
            }

        }
//        val intent = Intent(this, IntroduceActivity::class.java)
//        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu,menu)
//        val intent = Intent(this, IntroduceActivity::class.java)
//        startActivity(intent)
//        return super.onCreateOptionsMenu(menu)
//    }
}