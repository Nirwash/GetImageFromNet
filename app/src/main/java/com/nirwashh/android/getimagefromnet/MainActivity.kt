package com.nirwashh.android.getimagefromnet

import android.R
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.nirwashh.android.getimagefromnet.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private companion object {
        const val URL =
            "https://images4.alphacoders.com/109/1095230.jpg"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        //getImageFromNet()
        //getImageFromPicasso()
        getImageFromGlide()




    }

    private fun getImageFromGlide() {
        Glide.with(this).load(URL)
            .centerCrop()
            .override(720, 1080)
            .placeholder(R.drawable.ic_media_pause)
            .into(b.imgView)
    }

    private fun getImageFromPicasso() {
        Picasso.get().load(URL)
            .centerCrop()
            .resize(720, 1280)
            .placeholder(R.drawable.ic_media_pause)
            .error(R.drawable.ic_dialog_alert)
            .into(b.imgView)
    }

    private fun getImageFromNet() {
        val netImage = NetImage(URL, object : ImageCallback {
            override fun success(bitmap: Bitmap) = runOnUiThread {
                b.imgView.setImageBitmap(bitmap)
            }

            override fun failed() = runOnUiThread {
                Toast.makeText(this@MainActivity, "FAILED", Toast.LENGTH_SHORT).show()
            }
        })
        netImage.start()
    }
}