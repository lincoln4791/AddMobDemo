package com.example.addmobdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.addmobdemo.adsUtil.NativeAdUtil
import com.example.addmobdemo.databinding.ActivityNativeAddSmallBinding
import com.google.android.ads.nativetemplates.TemplateView

class NativeAddSmall : AppCompatActivity() {
    private lateinit var prefManager : PrefManager

    private lateinit var binding : ActivityNativeAddSmallBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeAddSmallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager(this)

        val lastAdShowDate = prefManager.lastNativeAdShownSmall

        if(Util.canAdShow(this,lastAdShowDate)){
            val nativead = NativeAdUtil(this)
            nativead.loadNativeAd(this,binding.myTemplate){
                if (it){
                    prefManager.lastNativeAdShownSmall = CurrentDate.currentTime24H
                }
            }
        }

    }
}