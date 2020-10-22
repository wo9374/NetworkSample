package com.example.networksample.NetworkAPI_Library

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.networksample.R
import kotlinx.android.synthetic.main.activity_networking_result.*
import org.json.JSONException
import java.net.URL

class VolleyExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking_result)

        //onCreate가 실행되면 RequestQueue에 요청이 전달되고 Volley가 자동으로 웹서버에 Request를 전달하여 응답을 받아와 화면에 출력.

        val requestQueue = Volley.newRequestQueue(this) //리퀘스트 요청객체 생성
        val url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                "location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBrJ3ec9wTuS6L-xHkaXLU8BJbFsx_LZ9o"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response -> //(람다) 응답 받았을 때 Response.Listener 자동 호출
                Log.d("요청결과", "VolleyExample onResponse")
                try {
                    val formattedResult = StringBuilder()
                    val responseJSONArray = response.getJSONArray("results")
                    for (i in 0 until responseJSONArray.length()) {
                        formattedResult.append("${responseJSONArray.getJSONObject(i)["name"]} => ${responseJSONArray.getJSONObject(i)["rating"]} \n")
                    }
                    tv_result.text = "List of Restaurants \nName\tRating \n \n$formattedResult"
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            })
        { error -> //(람다) 에러 발생시 Response.ErrorListener 호출
            Log.d("요청결과", "OkHttpExample onErrorResponse$error")
        }
        requestQueue.add(jsonObjectRequest)
    }
}