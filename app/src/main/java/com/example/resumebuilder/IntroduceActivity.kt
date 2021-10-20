package com.example.resumebuilder

import android.Manifest.permission.CAMERA
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.MediaController
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_introduce.*
import java.util.jar.Manifest

class IntroduceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduce)


        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(CAMERA), 111)
        } else {
            capPicture.isEnabled = true
            recBtn.isEnabled = true
        }

        capPicture.setOnClickListener{
            var image = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(image, 112)
        }

        recBtn.setOnClickListener{
            var video = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            startActivityForResult(video, 211)
        }

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 112) {
            var pic = data?.getParcelableExtra<Bitmap>("data")
            imageView.setImageBitmap(pic)
        } else if (requestCode == 211) {
            val uri = data?.data
            videoView.setVideoURI(uri)
            videoView.start()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            capPicture.isEnabled = true
            recBtn.isEnabled = true
        }
    }



}