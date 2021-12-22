package com.example.addmobdemo.adsUtil

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.addmobdemo.CurrentDate
import com.example.addmobdemo.PrefManager
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class InterstistialAdHelper(private val context: Context,private val activity:Activity,private var interstitialLastShown:String) {


    val prefManager = PrefManager(context)



     fun loadinterAd(callback : (isShown : Boolean)-> Unit){
        var mInterstitialAd: InterstitialAd? = null
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(context,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("tag", adError.message)
                mInterstitialAd = null
                callback(false)
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("tag", "InterAd was loaded.")
                mInterstitialAd = interstitialAd

                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        Log.d("tag", "InterAd was dismissed.")
                        callback(true)
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                        Log.d("tag", "InterAd failed to show.")
                        callback(false)
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d("tag", "InterAd showed fullscreen content.")
                        mInterstitialAd = null
                        prefManager.lastInterstitialAdShownMAI2= CurrentDate.currentTime24H
                    }
                }

                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(activity)
                    //callback(true)
                } else {
                    Log.d("tag", "The interstitial ad wasn't ready yet.")
                    callback(false)
                }
            }
        })

    }



}