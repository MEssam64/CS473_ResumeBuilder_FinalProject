package com.example.resumebuilder

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

class AboutMe : BaseFragment() {

//    val intent = Intent(this, IntroduceActivity::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_me, container, false)
    }


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.video_menu, menu)
//        return super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.introduce-> {
//                val intent = Intent(this, IntroduceActivity::class.java)
//                startActivity(intent)
//            }
//
//        }
////        val intent = Intent(this, IntroduceActivity::class.java)
////        startActivity(intent)
//        return super.onOptionsItemSelected(item)
//    }


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        menuInflater.inflate(R.menu.main_menu,menu)
//        val intent = Intent(this, IntroduceActivity::class.java)
//        startActivity(intent)
//        return super.onCreateOptionsMenu(menu)
//    }
}