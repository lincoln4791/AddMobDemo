package com.example.addmobdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.addmobdemo.databinding.ActivityMainBinding
import com.google.android.gms.ads.*

class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("tag","OnCreate Called")

        MobileAds.initialize(this)

        //Banner Add
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        binding.btnNext.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
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