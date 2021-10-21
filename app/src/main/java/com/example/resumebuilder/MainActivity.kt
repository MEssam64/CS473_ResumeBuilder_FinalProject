package com.example.resumebuilder

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
                    tab.text = "Experience & Education"
                }
                2 -> {
                    tab.text = "External Links"
                }
                3 -> {
                    tab.text = "Generate pdf"
                }
            }
        }.attach()

        val db = CVDataBase.invoke(this);


        val userDAO = db.getDao()

        GlobalScope.launch {

            userWithAllData=userDAO.getUserByEmail("MAbdelzaher@miu.edu")


        }

    }


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
        val filePath= Environment.getExternalStorageDirectory().toString()+"/" + fileName+".pdf"
        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(filePath))
            mDoc.open()
            mDoc.add(Paragraph("   "))
            mDoc.add(Paragraph("                                                           "+userWithAllData?.user?.firstName+" "+userWithAllData?.user?.lastName))
            mDoc.add(Paragraph("                                             "+userWithAllData?.user?.emailAddress +"   " +userWithAllData?.user?.phoneNumber))
            mDoc.add(Paragraph("                                                           "+userWithAllData?.user?.title))
            mDoc.add(Paragraph("   "))
            mDoc.add(Paragraph("   "))
            mDoc.add(Paragraph("Bio"))
            mDoc.add(Paragraph("--------------------------------------------------------------------------------------------------------------------------------"))
            mDoc.add(Paragraph(userWithAllData?.user?.bio))
            mDoc.add(Paragraph("   "))
            mDoc.add(Paragraph("   "))
            mDoc.add(Paragraph("EDUCATION"))
            mDoc.add(Paragraph("--------------------------------------------------------------------------------------------------------------------------------"))
            for (edu in userWithAllData!!.educations){

                mDoc.add(Paragraph("                                                   "+edu.schoolName+","+edu.location))
                // mDoc.add(Paragraph("                      "+edu.location))
                mDoc.add(Paragraph("                                                   "+edu.title))

            }
            mDoc.add(Paragraph("   "))
            mDoc.add(Paragraph("   "))
            mDoc.add(Paragraph("EXPERIENCE"))
            mDoc.add(Paragraph("--------------------------------------------------------------------------------------------------------------------------------"))
            for (exp in userWithAllData!!.experiences){

                mDoc.add(Paragraph("                                                   "+exp.companyName+""+exp.location))
                mDoc.add(Paragraph("                                                   "+exp.title))
                mDoc.add(Paragraph("                                                   "+exp.from.toString()+""+ exp.to.toString()))


            }

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