package com.example.ardianethread.Services

import android.content.Context
import org.json.JSONObject

class UserAPIController constructor(serviceInjection: UserInterface): UserInterface {
    private val service: UserInterface = serviceInjection

    override fun getOneByPhone(phone: String, completionHandler: (response: JSONObject?) -> Unit) {
        service.getOneByPhone( phone, completionHandler)    }

    override fun Post(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        completionHandler: (response: JSONObject?) -> Unit,
    ) {
        service.Post(firstName, lastName, email,phone,completionHandler)
    }

    override fun GetOneById(id: Int, completionHandler: (response: JSONObject?) -> Unit) {
        service.GetOneById(id, completionHandler)
    }
}