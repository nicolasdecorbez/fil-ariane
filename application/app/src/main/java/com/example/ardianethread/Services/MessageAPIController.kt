package com.example.ardianethread.Services

import org.json.JSONArray
import org.json.JSONObject

class MessageAPIController constructor(serviceInjection:MessageInterface): MessageInterface  {
    private val service: MessageInterface = serviceInjection
    override fun PostMessage(
        senderId: String,
        receiverId: String,
        msgContent: String,
        completionHandler: ( response: JSONObject? ) -> Unit,
    ) {
        service.PostMessage( senderId, receiverId, msgContent, completionHandler )
    }

    override fun GetAllSentByFriendId(
        friendId: Int,
        completionHandler: (response: JSONArray?) -> Unit,
    ) {
        service.GetAllSentByFriendId( friendId, completionHandler )
    }

    override fun GetAllReceivedbyFriendId(
        friendId: Int,
        completionHandler: (response: JSONArray?) -> Unit,
    ) {
        service.GetAllReceivedbyFriendId( friendId,completionHandler )
    }
}