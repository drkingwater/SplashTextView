package com.pxq.dexloader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.pxq.dexloader.dex.DexLoader
import com.pxq.dexloader.toast.IToast
import java.io.File

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dexPath = getFilesDir().getAbsolutePath()
        var optPath = getFilesDir().getAbsolutePath()

        val file = File(dexPath + "/Toast.jar")
        var loader = DexLoader(file.absolutePath, optPath, null, classLoader)

        Toast.makeText(this, "file : " + (file.length()), Toast.LENGTH_SHORT).show()
        Log.d(TAG, file.absolutePath)
        try {
            val clazz: Class<*>? = loader.findClass("com.pxq.dexloader.toast.ToastUtil")

            val instance = clazz?.newInstance()
            if (instance != null) {

                val toastUtils = instance as IToast
                toastUtils.showToast(this)
            } else {
                Toast.makeText(this, "dex not found", Toast.LENGTH_SHORT).show()
            }
        } catch (ex :Exception) {
            ex.printStackTrace()
            Toast.makeText(this, "dex not found", Toast.LENGTH_SHORT).show()
        }


    }
}
