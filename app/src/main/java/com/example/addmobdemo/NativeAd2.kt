package com.example.addmobdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.addmobdemo.adsUtil.NativeAdUtil
import com.example.addmobdemo.databinding.ActivityNativeAd2Binding
import com.google.android.ads.nativetemplates.TemplateView

class NativeAd2 : AppCompatActivity() {
    private lateinit var prefManager : PrefManager

    private lateinit var binding : ActivityNativeAd2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeAd2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager(this)

       val lastAdShowDate = prefManager.lastNativeAdShown

        if(Util.canAdShow(this,lastAdShowDate)){
            val nativead = NativeAdUtil(this)
            nativead.loadNativeAd(this,binding.myTemplate){
                if (it){
                    prefManager.lastNativeAdShown = CurrentDate.currentTime24H
                }
            }
        }
    }
}