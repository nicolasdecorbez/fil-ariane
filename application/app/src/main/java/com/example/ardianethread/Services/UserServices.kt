package com.example.ardianethread.Services

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ardianethread.Activities.MainActivity
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R
import org.json.JSONException
import org.json.JSONObject

class UserServices: Application() {

    object User {
        //POST USER FUNCTION
        fun Post(firstName: String, lastName: String, email: String, phone: String, context: Context): Boolean {
            val jsonBody: JSONObject = JSONObject()
            jsonBody.put("username", firstName)
            jsonBody.put("firstName", firstName)
            jsonBody.put("lastName", lastName)
            jsonBody.put("email", email)
            jsonBody.put("phone", phone)

            val queue = Volley.newRequestQueue(context)
            val url: String = "http://34.142.104.109/users"
            var success = false

            val strReq: JsonObjectRequest = object : JsonObjectRequest(Method.POST,
                url,
                jsonBody,
                Response.Listener { response ->
                    Log.e("TAG", "response: $response")

                    try {
                        Log.e("CREATED:",response.toString())
                        success = true
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
            queue.add(strReq)
            return success
        }
        //GET ALL USERS FUNCTION
        fun GetAll(context : Context): ArrayList<Users>{
            val url = "http:/34.142.104.109/users"
            val queue = Volley.newRequestQueue(context)
            var users : ArrayList<Users> = arrayListOf()

            val jsonObjectRequest = object : JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    try {
                        Log.e("Users :",response.toString())
                        for (i in 0 until response.length()){
                            // Get current json object
                            val userObject = response.getJSONObject(i)
                            var user = Users(
                                name = userObject.getString("lastName"),
                                firstname = userObject.getString("firstName"),
                                phone = userObject.getString("phone"),
                                email = userObject.getString("email"),
                                blood_type ="unknown",
                                hid ="unknown",
                            )
                            user.id = userObject.getInt("id")
                            users.add(user)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                },
                Response.ErrorListener { error ->
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                })
            {
                override fun getHeaders() : Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Host"] = "api.ardiane.com"
                    return params
                }
            }
            queue.add(jsonObjectRequest)
            return users
        }
        //GET USER BY PHONE FUNCTION
        fun GetOneByPhone(phone: String, context : Context) {
            val url = "http:/34.142.104.109/users/phone/$phone"
            val queue = Volley.newRequestQueue(context)


            val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->

                    try {
                        var user = Users(
                            name = response.getString("lastName"),
                            firstname = response.getString("firstName"),
                            phone = response.getString("phone"),
                            email = response.getString("email"),
                            blood_type ="unknown",
                            hid ="unknown",
                        )
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                })
            {
                override fun getHeaders() : Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Host"] = "api.ardiane.com"
                    return params
                }

            }
            Singleton.instance?.addToRequestQueue(jsonObjectRequest, ContentValues.TAG)
        }
        fun GetOneById(id: Int, context : Context): Users{
            val url = "http:/34.142.104.109/users/$id"
            val queue = Volley.newRequestQueue(context)
            var user = Users(
                name = "unknown",
                firstname = "unknown",
                phone = "unknown",
                email = "unknown",
                blood_type ="unknown",
                hid ="unknown",
            )

            val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    user.firstname = response.getString("firstName")
                    user.name = response.getString("lastName")
                    user.email = response.getString("email")
                    user.phone = response.getString("phone")
                    user.id = response.getInt("id")
                    try {

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                },
                Response.ErrorListener { error ->
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                })
            {
                override fun getHeaders() : Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Host"] = "api.ardiane.com"
                    return params
                }
            }
            queue.add(jsonObjectRequest)
            Log.e("User ",user.toString())
            return user
        }
        //UPDATE USER FUNCTION
        fun Put(firstName: String?, lastName: String?, email: String?, phone: String?, context: Context){
            val jsonBody: JSONObject = JSONObject()
            if( firstName != null ){
                jsonBody.put("username", firstName)
            }
            if( firstName != null ){
                jsonBody.put("firstName", firstName)
            }
            if( lastName != null ){
                jsonBody.put("lastName", lastName)
            }
            if( email != null ){
                jsonBody.put("email", email)
            }
            if( phone != null ){
                jsonBody.put("phone", phone)
            }
            val queue = Volley.newRequestQueue(context)
            val url: String = "http://34.142.104.109/users/$phone"

            val strReq: JsonObjectRequest = object : JsonObjectRequest(Method.PUT,
                url,
                jsonBody,
                Response.Listener { response ->
                    Log.e("TAG", "response: $response")
                    try {
                        Toast.makeText(context, "ntm", Toast.LENGTH_LONG).show()
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
            queue.add(strReq)
        }
        //DELETE USER FUNCTION
        fun Delete(context: Context,phone: String){
            val url = "http:/34.142.104.109/users/$phone"
            val queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = object : JsonObjectRequest(Request.Method.DELETE, url, null,
                Response.Listener { response ->
                    try {
                        Log.e("DELETE:",response.toString())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                })
            {
                override fun getHeaders() : Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Host"] = "api.ardiane.com"
                    return params
                }
            }
            queue.add(jsonObjectRequest)
        }
        fun Login(phone: String?,password: String?,context: Context){
            if(phone != null){
                val service = UserAPIService()
                val apiController = UserAPIController(service)
                apiController.getOneByPhone(phone){response ->

                    if (response != null) {
                        Global.Current.User.name = response.getString("lastName")
                        Global.Current.User.firstname = response.getString("firstName")
                        Global.Current.User.phone = response.getString("phone")
                        Global.Current.User.email = response.getString("email")
                        Global.Current.User.blood_type ="unknown"
                        Global.Current.User.hid ="unknown"
                        Global.Current.User.id = response.getString("id").toInt()
                    }
                }
                    Global.Current.isDefined = true
                    Global.Current.isLoged = true
                Log.i("INITIALIZED",Global.Current.User.firstname)
            }
        }
        fun Register(firstName: String, lastName: String, email: String, phone: String, context: Context){
            val service = UserAPIService()
            val apiController = UserAPIController(service)
               apiController.Post(firstName,lastName,email,phone){response ->
                   if (response != null) {
                       Global.Current.User.name = response.getString("lastName")
                       Global.Current.User.firstname = response.getString("firstName")
                       Global.Current.User.phone = response.getString("phone")
                       Global.Current.User.email = response.getString("email")
                       Global.Current.User.blood_type ="unknown"
                       Global.Current.User.hid ="unknown"
                   }
            }
            Log.e("Registered",Global.Current.User.toString())
            Global.Current.isDefined = true
            Global.Current.isLoged = true
        }
    }
}