package com.example.addmobdemo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import java.text.SimpleDateFormat

class Util {
    companion object{
        private val tag = "tag"

        fun loadNativeAd(context: Context,view: TemplateView){
            MobileAds.initialize(context)
            val adLoader: AdLoader = com.google.android.gms.ads.AdLoader.Builder(context,
                "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd { nativeAd ->
                    val styles =
                        NativeTemplateStyle.Builder().build()
                    view.setStyles(styles)
                    view.setNativeAd(nativeAd)
                }
                .build()

            adLoader.loadAd(AdRequest.Builder().build())
        }




        @SuppressLint("SimpleDateFormat")
        fun diffTime(time: String): Long {
            var min: Long = 0
            val difference: Long
            try {
                val simpleDateFormat = SimpleDateFormat("hh:mm aa") // for 12-hour system, hh should be used instead of HH
                // There is no minute different between the two, only 8 hours difference. We are not considering Date, So minute will always remain 0
                val date1 = simpleDateFormat.parse(time)
                val date2 = simpleDateFormat.parse(CurrentDate.currentTime24H)
                difference = (date2?.time!! - date1?.time!!) / 1000
                val hours = difference % (24 * 3600) / 3600 // Calculating Hours
                val minute = difference % 3600 / 60 // Calculating minutes if there is any minutes difference
                min = minute + hours * 60 // This will be our final minutes. Multiplying by 60 as 1 hour contains 60 mins
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            Log.d("tag", "$min of difference")
            return min
        }


        fun canAdShow(prefManager:PrefManager,interstitialLastShown:String) : Boolean{
            var canAdShow  = false
            if (!prefManager.isTestUser) {
                if (interstitialLastShown!="" && diffTime(interstitialLastShown)>=0){
                    if (diffTime(interstitialLastShown) >1L) {
                        info(tag," Ad shown because difference is greater than 1")
                        canAdShow = true
                    } else {
                        info(tag," AD not loaded because difference is less than 1")
                        canAdShow  = false
                    }
                } else {
                    info(tag," AD loaded because difference is either empty or less then 1")
                    canAdShow  = true
                }

            }
            else{
                info(tag," Ad not loaded for test user")
                canAdShow  = false
            }

            return canAdShow
        }

        fun info(tag: String, msg: String){
            if (BuildConfig.DEBUG) Log.i(tag, msg)
        }
    }



}