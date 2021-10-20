package com.example.resumebuilder

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.room.Room
import com.example.resumebuilder.models.CVDataBase
import com.example.resumebuilder.models.User
import com.example.resumebuilder.models.UserWithAllData
import com.google.android.material.tabs.TabLayoutMediator
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val STORAGE_CODE:Int=100
    var userWithAllData: UserWithAllData?=null;
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
                4 -> {
                 tab.text = "pdf"
               }
            }
        }.attach()

     /*   val db = Room.databaseBuilder(
            applicationContext,
            CVDataBase::class.java,
            "CVDataBase"
        ).allowMainThreadQueries().build()

        val userDAO = db.getDao()
        var ss = userDAO.getAllUsers()

      */



        val db = CVDataBase.invoke(this);


        val userDAO = db.getDao()

        GlobalScope.launch {
            var ss = userDAO.getAllUsers()
            if (ss.isEmpty()){
                userDAO.addUser(User("asrat","birhanu","akelilew@miu.edu","6418191529","","Java Developer",""))
            }
            userWithAllData=userDAO.getUserByEmail("akelilew@miu.edu")


        }

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




    fun toPdf(view: View) {
        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                val permission= arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission,STORAGE_CODE)
            }
            else{
                savePdf()
            }
        }
        else{
            savePdf()
        }

    }






    private fun savePdf() {
        val mDoc= Document()
        val fileName= SimpleDateFormat("yyyymmdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())
        val filePath= Environment.getExternalStorageDirectory().toString()+"/" + fileName+""
        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(filePath))
            mDoc.open()



            mDoc.add(Paragraph(userWithAllData?.user?.firstName+""+userWithAllData?.user?.lastName))
            /* mDoc.add(Paragraph(userWithAllData?.user?.emailAddress +"" +userWithAllData?.user?.phoneNumber))

             mDoc.add(Paragraph(userWithAllData?.user?.title))
             mDoc.add(Paragraph(userWithAllData?.user?.bio))

             for (edu in userWithAllData!!.educations){

                 mDoc.add(Paragraph(edu.schoolName))
                 mDoc.add(Paragraph(edu.location))
                 mDoc.add(Paragraph(edu.title))

             }
             for (exp in userWithAllData!!.experiences){

                 mDoc.add(Paragraph(exp.companyName+""+exp.location))
                 mDoc.add(Paragraph(exp.title))
                 mDoc.add(Paragraph(exp.from.toString()+""+ exp.to.toString()))


             }
 **/
            mDoc.close()
            Toast.makeText(this, "$fileName.pdf\n is saved to\n $filePath", Toast.LENGTH_LONG).show()

        }
        catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            STORAGE_CODE->{
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    savePdf()
                }
                else{
                    Toast.makeText(this,"permission denied....!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}