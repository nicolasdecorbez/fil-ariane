package com.example.ardianethread.Services

import android.app.Application
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
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class FriendServices: Application() {
    object Friend {
        //ADD FRIEND FUNCTION
        fun Post(friendId: Int, senderId: Int, context: Context) {
            val jsonBody: JSONObject = JSONObject()
            jsonBody.put("firstId", senderId)
            jsonBody.put("secondId", friendId)

            val queue = Volley.newRequestQueue(context)
            val url: String = "http:/34.142.104.109/friends"

            val strReq: JsonObjectRequest = object : JsonObjectRequest(Method.POST,
                url,
                jsonBody,
                Response.Listener { response ->
                    try {
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
            queue.add(strReq)
            queue.start()
        }

        fun GetAllFriends(userId: Int, context: Context){
            val service = FriendAPIService()
            val apiController = FriendAPIController(service)
            apiController.GetAllFriends(userId) { response ->

                if (response != null) {
                    for (i in 0 until response.length()) {
                        // Get current json object
                        var userID = response.getString(i).toInt()
                        Global.TempFriendUser.Ids.add(userID)
                    }
                    Global.TempFriendUser.idDefined = true
                }
            }
            Log.e("Inflated Array",Global.TempFriendUser.Ids.toString())
        }
        fun SetFriendsArray(id: Int,context : Context) {

            val userService = UserAPIService()
            val userController = UserAPIController(userService)
            userController.GetOneById(id) { response ->
                Log.e("Response User", response.toString())
                if (response != null) {
                    var user = Users(
                        name = response.getString("lastName"),
                        firstname = response.getString("firstName"),
                        phone = response.getString("phone"),
                        email = response.getString("email"),
                        blood_type = "unknown",
                        hid = "unknown",
                    )
                    user.id = response.getString("id").toInt()
                    Global.TempFriendUser.UsersArray.add(user)
                }
                Log.e("Global User", Global.TempFriendUser.Users.toString())
            }
        }
        fun addFriend(senderId: Int,phone: String,context: Context){
            val userservice = UserAPIService()
            var friendId = 0
            val userController = UserAPIController(userservice)
            userController.getOneByPhone(phone){response ->
                if(response!=null) {
                    friendId = response.getString("id").toInt()
                }
                val friendService = FriendAPIService()
                val friendController = FriendAPIController(friendService)
                friendController.Post(senderId,friendId){response ->
                    Log.e("CREATED:", response.toString())
                }
            }
            val friendservice = FriendAPIService()
            val friendController = FriendAPIController(friendservice)
            friendController.Post(friendId,senderId) { response ->
                if (response != null) {
                    Log.e("Succed ", response.toString())
                } else {
                    Log.e("Fail", response.toString())
                }
            }
        }
    }
}