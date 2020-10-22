package com.example.networksample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.networksample.NetworkAPI_Library.AndroidAsyncHttpExample
import com.example.networksample.NetworkAPI_Library.FastAndroidNetworkingExample
import com.example.networksample.NetworkAPI_Library.OKHttpExample
import com.example.networksample.NetworkAPI_Library.VolleyExample
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_okHttp3.setOnClickListener {
            startActivity(Intent(this, OKHttpExample::class.java))
        }

        bt_volley.setOnClickListener {
            startActivity(Intent(this, VolleyExample::class.java))
        }

        bt_android_async_http.setOnClickListener {
            startActivity(Intent(this, AndroidAsyncHttpExample::class.java))
        }

        bt_fast_android_networking.setOnClickListener {
            startActivity(Intent(this, FastAndroidNetworkingExample::class.java))
        }
    }
}