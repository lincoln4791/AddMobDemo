package com.example.addmobdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.ads.nativetemplates.TemplateView

class NativeAddSmall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_add_small)

        findViewById<Button>(R.id.btn).setOnClickListener {
            startActivity(Intent(this,NativeAd2::class.java))
            finish()
        }

        Util.loadNativeAd(this,findViewById<TemplateView>(R.id.my_template))

    }
}