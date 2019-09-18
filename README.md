# SplashTextView
文本闪烁
# 效果

# 用法


        <com.pxq.myapplication.widget.SplashTextView
             android:id="@+id/splash_tv"
             android:layout_width="200dp"
             android:layout_height="100dp"
             android:layout_centerInParent="true"/>
        
        val splashTv = findViewById<SplashTextView>(R.id.splash_tv)
        splashTv.setText("文本闪烁效果")
