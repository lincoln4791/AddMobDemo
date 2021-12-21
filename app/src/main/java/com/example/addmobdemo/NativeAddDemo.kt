package com.example.addmobdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.*
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import android.widget.TextView

import android.widget.RatingBar

import android.R
import android.view.View
import android.widget.Button
import android.widget.ImageView

import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.AdListener

import com.google.android.gms.ads.AdLoader

import android.widget.FrameLayout
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener


class NativeAddDemo : AppCompatActivity() {
    private lateinit var nativeAd : NativeAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.addmobdemo.R.layout.activity_native_add_demo)

        MobileAds.initialize(this)
        refreshAd();

    }

    private fun populateUnifiedNativeAdView(
        nativeAd: NativeAd,
        adView: NativeAdView,
    ) {
        adView.mediaView = adView.findViewById(com.example.addmobdemo.R.id.ad_media) as MediaView
        adView.headlineView = adView.findViewById(com.example.addmobdemo.R.id.ad_headline)
        adView.bodyView = adView.findViewById(com.example.addmobdemo.R.id.ad_body)
        adView.callToActionView = adView.findViewById(com.example.addmobdemo.R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(com.example.addmobdemo.R.id.ad_app_icon)
        adView.priceView = adView.findViewById(com.example.addmobdemo.R.id.ad_price)
        adView.advertiserView = adView.findViewById(com.example.addmobdemo.R.id.ad_advertiser)
        adView.storeView = adView.findViewById(com.example.addmobdemo.R.id.ad_store)
        adView.starRatingView = adView.findViewById(com.example.addmobdemo.R.id.ad_stars)
        (adView.headlineView as TextView).text = nativeAd.headline
        adView.mediaView.setMediaContent(nativeAd.mediaContent)
        if (nativeAd.body == null) {
            adView.bodyView.visibility = View.INVISIBLE
        } else {
            adView.bodyView.visibility = View.VISIBLE
            (adView.bodyView as TextView).text = nativeAd.body
        }
        if (nativeAd.callToAction == null) {
            adView.callToActionView.visibility = View.INVISIBLE
        } else {
            adView.callToActionView.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }
        if (nativeAd.icon == null) {
            adView.iconView.visibility = View.GONE
        } else {
            (adView.iconView as ImageView).setImageDrawable(nativeAd.icon.drawable)
            adView.iconView.visibility = View.VISIBLE
        }
        if (nativeAd.price == null) {
            adView.priceView.visibility = View.INVISIBLE
        } else {
            adView.priceView.visibility = View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }
        if (nativeAd.store == null) {
            adView.storeView.visibility = View.INVISIBLE
        } else {
            adView.storeView.visibility = View.VISIBLE
            (adView.storeView as TextView).text = nativeAd.store
        }
        if (nativeAd.starRating == null) {
            adView.starRatingView.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating.toFloat()
            adView.starRatingView.visibility = View.VISIBLE
        }
        if (nativeAd.advertiser == null) {
            adView.advertiserView.visibility = View.INVISIBLE
        } else {
            (adView.advertiserView as TextView).text = nativeAd.advertiser
            adView.advertiserView.visibility = View.VISIBLE
        }
        adView.setNativeAd(nativeAd)
    }

    private fun refreshAd() {
        val builder = AdLoader.Builder(this, getString(com.example.addmobdemo.R.string.ADMOB_ADS_UNIT_ID))
        builder.forNativeAd { nAd ->
            if (::nativeAd.isInitialized) {
                nativeAd.destroy()
            }
            nativeAd = nAd
            val frameLayout = findViewById<FrameLayout>(com.example.addmobdemo.R.id.fl_adplaceholder)
            val adView: NativeAdView =
                layoutInflater.inflate(com.example.addmobdemo.R.layout.ad_unified, null) as NativeAdView
            populateUnifiedNativeAdView(nAd, adView)
            frameLayout.removeAllViews()
            frameLayout.addView(adView)
        }
        val adOptions = NativeAdOptions.Builder().build()
        builder.withNativeAdOptions(adOptions)
        val adLoader = builder.withAdListener(object : AdListener() {
            fun onAdFailedToLoad(i: Int) {}
        }).build()
        adLoader.loadAd(AdRequest.Builder().build())
    }


}