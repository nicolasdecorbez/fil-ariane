package com.example.ardianethread.Services

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class FriendAPIController constructor(serviceInjection: FriendInterface): FriendInterface  {
    private val service: FriendInterface = serviceInjection

    override fun GetAllFriends(userId: Int, completionHandler: (response: JSONArray?) -> Unit) {
        service.GetAllFriends(userId,completionHandler)
    }

    override fun Post(
        friendId: Int,
        senderId: Int,
        completionHandler: (response: JSONObject?) -> Unit,
    ) {
        service.Post(friendId,senderId,completionHandler)
    }
}