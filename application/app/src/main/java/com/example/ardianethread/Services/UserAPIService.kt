package com.example.ardianethread.Services

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ardianethread.Globals.Global
import org.json.JSONException
import org.json.JSONObject

class UserAPIService: UserInterface {
        val TAG = UserAPIService::class.java.simpleName


    override fun getOneByPhone(phone: String, completionHandler: (response: JSONObject?) -> Unit) {
        val basePath = "http:/34.142.104.109/users/phone/$phone"
        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, basePath, null,
            Response.Listener<JSONObject> { response ->
                Log.d(TAG, "/post request OK! Response: $response")
                completionHandler(response)
            },
            Response.ErrorListener { error ->
                VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                completionHandler(null)
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Host"] = "api.ardiane.com"
                return headers
            }
        }
        Singleton.instance?.addToRequestQueue(jsonObjectRequest, TAG)
        Singleton.instance?.requestQueue?.start()
    }

    override fun Post(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        completionHandler: (response: JSONObject?) -> Unit,
    ) {
        val jsonBody: JSONObject = JSONObject()
        jsonBody.put("username", firstName)
        jsonBody.put("firstName", firstName)
        jsonBody.put("lastName", lastName)
        jsonBody.put("email", email)
        jsonBody.put("phone", phone)

        val url: String = "http://34.142.104.109/users"

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

    override fun GetOneById(id: Int, completionHandler: (response: JSONObject?) -> Unit) {
        val url = "http:/34.142.104.109/users/$id"
        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->

                try {
                    completionHandler(response)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            },
            Response.ErrorListener { error ->
                Log.e("TAG", "problem occurred")
            })
        {
            override fun getHeaders() : Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Host"] = "api.ardiane.com"
                return params
            }
        }
        Singleton.instance?.addToRequestQueue(jsonObjectRequest, TAG)
        Singleton.instance?.requestQueue?.start()
    }
}
