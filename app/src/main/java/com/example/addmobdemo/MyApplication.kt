package com.example.addmobdemo

import android.app.Application
import com.example.addmobdemo.adsUtil.AppOpenManager

import com.google.android.gms.ads.MobileAds


class MyApplication : Application() {
    private var appOpenManager: AppOpenManager? = null
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(
            this
        ) { }

        appOpenManager =  AppOpenManager(this)

    }
}