package com.example.addmobdemo.adsUtil

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*

class NativeAdUtil(private val context: Context) {
    private val TAG = "NativeAd"
    private lateinit var adLoader: AdLoader

    fun loadNativeAd(activity : Activity,view: TemplateView,callback : (isAdLoaded : Boolean)-> Unit){
        MobileAds.initialize(context)
         adLoader = AdLoader.Builder(context,
            "ca-app-pub-3940256099942544/2247696110")
            .forNativeAd { nativeAd ->


                if (adLoader.isLoading) {
                    // The AdLoader is still loading ads.
                    // Expect more adLoaded or onAdFailedToLoad callbacks.
                } else {
                    // The AdLoader has finished loading ads.
                    val styles =
                        NativeTemplateStyle.Builder().build()
                    view.setStyles(styles)
                    view.setNativeAd(nativeAd)
                    view.visibility = View.VISIBLE
                }

                if (activity.isDestroyed) {
                    nativeAd.destroy()
                    return@forNativeAd
                }

            }
            .withAdListener(object : AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Log.d(TAG,"Native Ad Loaded")
                    callback(true)
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    Log.d(TAG,"Native Ad Failed")
                    callback(false)
                }

            })
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }
}