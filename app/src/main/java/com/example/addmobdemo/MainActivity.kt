package com.example.addmobdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.size
import com.example.addmobdemo.databinding.ActivityMainBinding
import com.google.android.gms.ads.*
import android.util.DisplayMetrics




class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("tag","OnCreate Called")

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels




        //Banner Add
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        MobileAds.initialize(this)

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }

        binding.btnNext2.setOnClickListener {
            startActivity(Intent(this,NativeAddDemo::class.java))
            finish()
        }


        binding.btnAdative.setOnClickListener {
            startActivity(Intent(this,AdaptiveBanner::class.java))
            finish()
        }

        mAdView.adListener = object: AdListener(){
            override fun onAdLoaded() {
                //Toast.makeText(this@MainActivity, "Add clicked",Toast.LENGTH_SHORT).show()
                Log.d("tag","Banner Loaded")
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
                Log.d("tag","Banner Failed")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.d("tag","Banner Opened")
            }

            override fun onAdClicked() {
                Toast.makeText(this@MainActivity, "Add clicked",Toast.LENGTH_SHORT).show()
                Log.d("tag","banner Clicked")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Toast.makeText(this@MainActivity, "Add clicked",Toast.LENGTH_SHORT).show()
                Log.d("tag","banner Closed")
            }
        }
    }
}