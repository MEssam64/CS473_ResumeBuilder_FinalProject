package com.example.resumebuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
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
            }
        }.attach()
    }
}