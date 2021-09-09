package com.example.ardianethread.Services


import org.json.JSONArray
import org.json.JSONObject

interface MessageInterface {
    fun PostMessage(senderId: String, receiverId: String, msgContent: String, completionHandler: (response: JSONObject?) -> Unit)
    fun GetAllSentByFriendId(friendId: Int,completionHandler: (response: JSONArray?) -> Unit)
    fun GetAllReceivedbyFriendId(friendId: Int,completionHandler: (response: JSONArray?) -> Unit)
    }