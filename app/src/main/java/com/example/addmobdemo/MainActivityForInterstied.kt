package com.example.addmobdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.addmobdemo.adsUtil.BannerAddHelper
import com.example.addmobdemo.databinding.ActivityMainForInterstiedBinding
import com.google.android.gms.ads.AdView

class MainActivityForInterstied : AppCompatActivity() {

    lateinit var mAdView : AdView
    lateinit var prefManager : PrefManager

    private lateinit var binding : ActivityMainForInterstiedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainForInterstiedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager(this)


        val lastAddShownDate = prefManager.lastInterstitialAdShownBANNER
        if(Util.canAdShow(this,lastAddShownDate)){
            val bannerHelper = BannerAddHelper(this@MainActivityForInterstied)
            bannerHelper.bannerAd(binding.adView){
                if (it){
                    prefManager.lastInterstitialAdShownBANNER = CurrentDate.currentTime24H
                }
            }
        }


    }
}