package com.example.ardianethread.Services

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ardianethread.Data.Messages
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import org.json.JSONException
import org.json.JSONObject

class MessageServices:Application() {
    object Message {
        //POST MESSAGE FUNCTION
        fun Post(senderId: String, receiverId: String, msgContent: String,context: Context) {
            val jsonBody: JSONObject = JSONObject()
            jsonBody.put("senderId", senderId)
            jsonBody.put("receiverId", receiverId)
            jsonBody.put("msgContent", msgContent)

            val queue = Volley.newRequestQueue(context)
            val url: String = "http://34.142.104.109/message"

            val strReq: JsonObjectRequest = object : JsonObjectRequest(Method.POST,
                url,
                jsonBody,
                Response.Listener { response ->
                    Log.e("TAG", "response: $response")

                    try {
                        Log.e("CREATED:",response.toString())
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
        //GET ALL SENT MESSAGES OF AN USER BY ID FUNCTION
        fun ParseFriendArray(friendId: Int,context : Context){
            val messageService = MessageAPIService()
            val messageController = MessageAPIController(messageService)
            Global.Current.User.id?.let {
                messageController.GetAllSentByFriendId(it){ response ->
                    if (response != null) {
                        for (i in 0 until response.length()) {
                            var messageObject = response.getJSONObject(i)
                            var receiverId = messageObject.getString("receiverId").toInt()
                            if (receiverId == friendId){
                                var message = Messages(
                                    contact = friendId.toString(),
                                    content = messageObject.getString("content"),
                                )
                                Global.BuiltConversation.FriendMessageArray.add(message)
                            }
                        }
                        Global.BuiltConversation.initFriendArray = true
                    }
                }
            }
        }
        fun ParseMyMessageArray(friendId: Int,context : Context){
            val messageService = MessageAPIService()
            val messageController = MessageAPIController(messageService)
                messageController.GetAllSentByFriendId(friendId){ response ->
                    if (response != null) {
                        for (i in 0 until response.length()) {
                            var messageObject = response.getJSONObject(i)
                            var receiverId = messageObject.getString("receiverId").toInt()
                            if (receiverId == Global.Current.User.id){
                                var message = Messages(
                                    contact = friendId.toString(),
                                    content = messageObject.getString("content"),
                                )
                                Global.BuiltConversation.MyMessageArray.add(message)
                            }
                        }
                        Global.BuiltConversation.initMyArray = true
                    }
                }
        }
//GET ALL MESSAGE RECEIVER BY USER ID
       /* fun GetAllReceivedbyFriendId(friendId: Int,context : Context): ArrayList<Messages>{
            val url = "http:/34.142.104.109/message/receiver/$friendId"
            val queue = Volley.newRequestQueue(context)
            var messages : ArrayList<Messages> = arrayListOf()

            val jsonObjectRequest = object : JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    try {
                        Log.e("Users :",response.toString())
                        for (i in 0 until response.length()){
                            // Get current json object
                            val userObject = response.getJSONObject(i)
                            if (userObject.getInt("senderid") == Global.Current.User.id) {
                                UserServices.User.GetOneById(userObject.getInt("receiverId"),context)
                                val message = Messages(
                                    content = userObject.getString("messageContent"),
                                    contact =  Global.TempFriendUser.Users.firstname
                                )
                                message.senderId = userObject.getInt("senderId")
                                message.receiverId = userObject.getInt("receiverId")
                                messages.add(message)
                            }
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
            return messages
        }*/

        fun BuildConversation(MyArray : ArrayList<Messages>,FriendArray: ArrayList<Messages>) {

            if (MyArray.size > FriendArray.size) {
                for (i in 0 until MyArray.size) {
                    if (MyArray[i] != null) {
                        Global.BuiltConversation.ConversationArray.add(MyArray[i])
                    }
                    if (FriendArray[i] != null) {
                        Global.BuiltConversation.ConversationArray.add(FriendArray[i])
                    }
                }
            } else {
                for (i in 0 until FriendArray.size) {
                    if (FriendArray[i] != null) {
                        Global.BuiltConversation.ConversationArray.add(FriendArray[i])
                    }
                    if (MyArray[i] != null) {
                        Global.BuiltConversation.ConversationArray.add(MyArray[i])
                    }
                }
            }
        }
    }
}