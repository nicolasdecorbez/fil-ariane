package com.example.ardianethread.Services

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class FriendAPIService: FriendInterface {
    val TAG = FriendAPIService::class.java.simpleName

    override fun GetAllFriends(userId: Int, completionHandler: (response: JSONArray?) -> Unit) {
        val url = "http://34.142.104.109/friends/$userId"
        val jsonObjectRequest = object : JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    completionHandler(response)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
            }) {
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Host"] = "api.ardiane.com"
                return params
            }
        }
        Singleton.instance?.addToRequestQueue(jsonObjectRequest, TAG)
    }

    override fun Post(
        friendId: Int,
        senderId: Int,
        completionHandler: (response: JSONObject?) -> Unit,
    ) {
        val jsonBody: JSONObject = JSONObject()
        jsonBody.put("firstId", senderId)
        jsonBody.put("secondId", friendId)
        val url: String = "http:/34.142.104.109/friends"

        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(Method.POST,
            url,
            jsonBody,
            Response.Listener { response ->
                try {
                    completionHandler(response)
                    Log.e("CREATED:", response.toString())
                } catch (e: Exception) {
                    Log.e("TAG", "problem occurred")
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError ->
                Log.e("TAG", "problem occurred, volley error: " + volleyError.message)
            }) {
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers["Host"] = "api.ardiane.com"
                return headers
            }
        }
        Singleton.instance?.addToRequestQueue(jsonObjectRequest, TAG)
    }
}