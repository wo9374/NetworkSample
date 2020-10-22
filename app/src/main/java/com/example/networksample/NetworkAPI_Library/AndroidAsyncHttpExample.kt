package com.example.networksample.NetworkAPI_Library

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.networksample.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_networking_result.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class AndroidAsyncHttpExample : AppCompatActivity() {

    companion object {
        private const val url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBrJ3ec9wTuS6L-xHkaXLU8BJbFsx_LZ9o"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking_result)

        AndroidAsyncHttpClient[url, null, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
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
                Log.d("요청결과", "AndroidAsyncHttpExample Type : post, Result : onSuccess, State Code : $statusCode")
                // If the response is JSONObject instead of expected JSONArray
            }

            override fun onSuccess(statusCode: Int, headers: Array<Header>, timeline: JSONArray) {
                // Pull out the first event on the public timeline
            }
        }]
    }

    object AndroidAsyncHttpClient {
        private val client = AsyncHttpClient()
        operator fun get(url: String, params: RequestParams?, responseHandler: AsyncHttpResponseHandler?) {
            client[getAbsoluteUrl(url), params, responseHandler]
        }

        fun post(url: String, params: RequestParams?, responseHandler: AsyncHttpResponseHandler?) {
            client.post(getAbsoluteUrl(url), params, responseHandler)
        }

        private fun getAbsoluteUrl(relativeUrl: String): String {
            return relativeUrl
        }
    }
}