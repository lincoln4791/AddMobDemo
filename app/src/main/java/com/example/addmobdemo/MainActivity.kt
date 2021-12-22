package com.example.addmobdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.addmobdemo.databinding.ActivityMainBinding
import com.google.android.gms.ads.*
import android.util.DisplayMetrics


class MainActivity : AppCompatActivity() {
    lateinit var mAdView : AdView
    lateinit var prefManager : PrefManager

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager(this)

        Log.d("tag","OnCreate Called")

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels


        //Banner Add
        mAdView = findViewById(R.id.adView)

/*        Util.BannerAd(this,mAdView){

        }*/


        binding.btnNext.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }

        binding.btnNextInter.setOnClickListener {



            val lastAdShown = prefManager.lastInterstitialAdShownMAI2
            if(Util.canAdShow(prefManager,lastAdShown)){
                val interAd = InterstistialAdHelper(this@MainActivity, this@MainActivity, lastAdShown)
                interAd.loadinterAd {
                    navigate()
                }
            }
            else{
                navigate()
            }
        }



        binding.btnNext2.setOnClickListener {
            startActivity(Intent(this,NativeAddDemo::class.java))
            finish()
        }


        binding.btnAdative.setOnClickListener {
            startActivity(Intent(this,AdaptiveBanner::class.java))
            finish()
        }

    }

    private fun navigate(){
        startActivity(Intent(this,MainActivityForInterstied::class.java))
        //finish()
    }


  /*  fun loedIntertiatedAd(){

        val adRequest = AdRequest.Builder().build()


        InterstitialAd.load(this@MainActivity,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("tag", adError.message)
                mInterstitialAd = null

            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("tag", "InterAd was loaded.")
                mInterstitialAd = interstitialAd
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        Log.d("tag", "InterAd was dismissed.")
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                        Log.d("tag", "InterAd failed to show.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d("tag", "InterAd showed fullscreen content.")
                        mInterstitialAd = null
                    }
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(this@MainActivity)
                    //callback(true)
                } else {
                    Log.d("tag", "The interstitial ad wasn't ready yet.")
                }
            }
        })



    }*/


}