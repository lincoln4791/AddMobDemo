package com.example.addmobdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.ads.nativetemplates.TemplateView

class NativeAd2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad2)

        findViewById<Button>(R.id.btn).setOnClickListener {
            startActivity(Intent(this,NativeAddSmall::class.java))
            finish()
        }

        Util.loadNativeAd(this,findViewById<TemplateView>(R.id.my_template))

    }
}