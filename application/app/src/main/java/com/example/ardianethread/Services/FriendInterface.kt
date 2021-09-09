package com.example.ardianethread.Services

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

interface FriendInterface {
    fun GetAllFriends(userId: Int, completionHandler: (response: JSONArray?) -> Unit)
    fun Post(friendId: Int, senderId: Int, completionHandler: (response: JSONObject?) -> Unit)
}