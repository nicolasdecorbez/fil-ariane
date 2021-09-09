package com.example.ardianethread.Services

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class JourneyServices {
    object Journey {
        fun PostJourney(ardianeId: Int, theseusId: Int, returnDate: String, latitude: Float, longitude:Float, context: Context): Boolean
        {
            val jsonBody: JSONObject = JSONObject()
            jsonBody.put("ardianeId", ardianeId)
            jsonBody.put("theseusId", theseusId)
            jsonBody.put("returnDate", returnDate)
            jsonBody.put("latitude", latitude)
            jsonBody.put("longitude", longitude)

            val queue = Volley.newRequestQueue(context)
            val url: String = "http://34.142.104.109/journeys"
            var success = false

            Log.e("DEBUG 1", "jsonBody = $jsonBody, QUEUE = $queue")
            val strReq: JsonObjectRequest = object : JsonObjectRequest(Method.POST,
                url,
                jsonBody,
                Response.Listener { response ->
                    Log.e("JOURNEY", "onclick:eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
                    Log.e("JOURNEY", "onclick: $response")

                    try {
                        Log.e("JOURNEY CREATED:", response.toString())
                        success = true
                    } catch (e: Exception) {
                        Log.e("JOURNEY", "problem occurred : $response")
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { volleyError ->
                    Log.e("JOURNEY", "problem occurred, volley error: " + volleyError.message)
                }) {
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = HashMap()
                    headers["Host"] = "api.ardiane.com"
                    Log.e("DEBUG 3", "HEADERS = $headers")
                    return headers
                }
            }

            queue.add(strReq)
            Log.e("DEBUG 2", strReq.toString())
            return success
        }
    }
}