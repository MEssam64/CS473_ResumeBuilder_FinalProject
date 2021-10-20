package com.example.resumebuilder

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyViewAdapter (fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter (fm,lc) {
    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> AboutMe()
            1 -> WorkExperience()
            2 -> Education()
            3 -> ExternalLinks()
            4-> PdfFragment()
            else -> Fragment()
        }
    }
}