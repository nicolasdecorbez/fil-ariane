package com.example.ardianethread.Services

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.ardianethread.Data.Messages
import com.example.ardianethread.Globals.Global
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MessageAPIService: MessageInterface {
    val TAG = MessageAPIService::class.java.simpleName
    override fun PostMessage(
        senderId: String,
        receiverId: String,
        msgContent: String,
        completionHandler: (response: JSONObject?) -> Unit,
    ) {
        val jsonBody: JSONObject = JSONObject()
        jsonBody.put("senderId", senderId)
        jsonBody.put("receiverId", receiverId)
        jsonBody.put("msgContent", msgContent)
        val url: String = "http://34.142.104.109/message"

        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(Method.POST,
            url,
            jsonBody,
            Response.Listener { response ->
                Log.e("TAG", "response: $response")
                completionHandler(response)
                try {
                    Log.e("CREATED:",response.toString())
                } catch (e: Exception) {
                    Log.e("TAG", "problem occurred")
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError ->
                completionHandler(null)
                Log.e("TAG", "problem occurred, volley error: " + volleyError.message)
            }) {
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers["Host"] = "api.ardiane.com"
                return headers
            }
        }
        Singleton.instance?.addToRequestQueue(jsonObjectRequest, TAG)
        Singleton.instance?.requestQueue?.start()
    }

    override fun GetAllSentByFriendId(
        friendId: Int,
        completionHandler: (response: JSONArray?) -> Unit,
    ) {
        val url = "http:/34.142.104.109/message/sender/${friendId}"
        val jsonObjectRequest = object : JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    Log.e("Users :", response.toString())
                    completionHandler(response)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                completionHandler(null)
            }) {
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Host"] = "api.ardiane.com"
                return params
            }
        }
        Singleton.instance?.addToRequestQueue(jsonObjectRequest, TAG)
    }

    override fun GetAllReceivedbyFriendId(
        friendId: Int,
        completionHandler: (response: JSONArray?) -> Unit,
    ) {
        val url = "http:/34.142.104.109/message/receiver/${friendId}"
        val jsonObjectRequest = object : JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    Log.e("Users :", response.toString())
                    completionHandler(response)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                completionHandler(null)
            }) {
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Host"] = "api.ardiane.com"
                return params
            }
        }
        Singleton.instance?.addToRequestQueue(jsonObjectRequest, TAG)
    }
}