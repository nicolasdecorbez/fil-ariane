package com.example.ardianethread.Data

data class Users(var name : String , var firstname : String, var phone : String, var email : String, var blood_type: String, var hid: String ) {
    constructor(user: Users) : this(name = user.name, firstname = user.firstname,  phone = user.phone, email = user.email, blood_type = user.blood_type, hid= user.hid)
    public fun setUser(user : Users){
    }
}
