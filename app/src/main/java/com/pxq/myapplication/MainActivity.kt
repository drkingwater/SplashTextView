package com.pxq.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pxq.myapplication.widget.SplashTextView

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val splashTv = findViewById<SplashTextView>(R.id.splash_tv)
        splashTv.setText("文本闪烁效果")
    }

}