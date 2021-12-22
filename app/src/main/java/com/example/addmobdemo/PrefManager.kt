package com.example.addmobdemo

import android.content.Context
import android.content.SharedPreferences

class PrefManager (context: Context) {
     var pref: SharedPreferences
     var editor: SharedPreferences.Editor
     val PRIVATE_MODE = 0

     init {
         pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
         editor = pref.edit()
     }


    var isTestUser:Boolean
        get() = pref.getBoolean("T_USER",false)
        set(value) {editor.putBoolean("T_USER",value).commit()}


    var lastInterstitialAdShownMAI2:String
        get() = pref.getString("mai2","")!!
        set(value) {editor.putString("mai2",value).commit()}

    var lastInterstitialAdShownBANNER:String
        get() = pref.getString("mai2Banner","")!!
        set(value) {editor.putString("mai2Banner",value).commit()}

    var lastNativeAdShown:String
        get() = pref.getString("lastNativeAdShown","")!!
        set(value) {editor.putString("lastNativeAdShown",value).commit()}

    var lastNativeAdShownSmall:String
        get() = pref.getString("lastNativeAdShownSmall","")!!
        set(value) {editor.putString("lastNativeAdShownSmall",value).commit()}


    companion object{
        private val PREF_NAME = "PatientAid"
    }
}