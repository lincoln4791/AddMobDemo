package com.example.addmobdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.addmobdemo.databinding.ActivityMain2Binding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity2 : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null

    private lateinit var binding : ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this)

        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("tag", adError.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("tag", "Ad was loaded.")
                mInterstitialAd = interstitialAd

                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(this@MainActivity2)
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.")
                }

            }
        })


        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d("tag", "Ad was dismissed.")
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                Log.d("tag", "Ad failed to show.")
            }

            override fun onAdShowedFullScreenContent() {
                Log.d("tag", "Ad showed fullscreen content.")
                mInterstitialAd = null
            }
        }


        binding.btnBack.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
}