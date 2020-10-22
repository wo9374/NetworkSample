package com.example.networksample.NetworkAPI_Library

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.networksample.R
import kotlinx.android.synthetic.main.activity_networking_result.*
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class OKHttpExample : AppCompatActivity() {
    private val url =
        "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBrJ3ec9wTuS6L-xHkaXLU8BJbFsx_LZ9o"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking_result)

        val client = OkHttpClient()
        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        //비동기 처리 enqueue사용
        client.newCall(request).enqueue(object : Callback {
            // OkHttpClient에 사용자가 정의한 Requst Call

            override fun onFailure(call: Call, e: IOException) {
                Log.d("요청결과", "onFailure")
                call.cancel()
            }

            override fun onResponse(call: Call, response: Response) {
                val myResponse = response.body!!.string() // 응답된 Json Data의 Body를 저장
                runOnUiThread {
                    Log.d("요청결과", "OkHttpExample onResponse")
                    try {
                        val formattedResult = StringBuilder() // Text에 set할 StringBuilder 선언
                        val jsonObject = JSONObject(myResponse)
                        val responseJSONArray = jsonObject.getJSONArray("results")
                        for (i in 0 until responseJSONArray.length()) {
                            formattedResult.append("${responseJSONArray.getJSONObject(i)["name"]} => ${responseJSONArray.getJSONObject(i)["rating"]} \n")
                        }
                        tv_result.text = "List of Restaurants \nName\tRating \n \n$formattedResult"
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }//runOnUiThread
            }//onResponse
        })//enqueue
    }
}