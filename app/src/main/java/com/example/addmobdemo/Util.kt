package com.example.addmobdemo

import android.content.Context
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class Util {
    companion object{
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
    }
}