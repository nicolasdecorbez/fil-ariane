package com.example.ardianethread.Services

import android.content.Context
import com.example.ardianethread.Data.Users
import org.json.JSONObject

interface UserInterface {
    fun getOneByPhone( phone: String, completionHandler: (response: JSONObject?) -> Unit)
    fun Post(firstName: String, lastName: String, email: String, phone: String,completionHandler: (response: JSONObject?) -> Unit)
    fun GetOneById(id: Int, completionHandler: (response: JSONObject?) -> Unit)
    }