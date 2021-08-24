package com.example.ardianethread.Globals

import android.app.Application
import com.example.ardianethread.Data.Journey
import com.example.ardianethread.Data.Users

class Global : Application() {
    object Current {
        var User : Users = Users(
            name = "0",
            firstname = "0",
            phone = "0",
            email = "0",
            blood_type = "0",
            hid = "0"
        )
        var isDefined :Boolean = false
    }
   object Corres {
       var name = ""
   }
    object CurrentJourneys {
        var GlobalJourneyList : ArrayList<Journey> = arrayListOf()
        var isJourneyDefined = false

        fun  inflateJourneyInfo(friend:String, timer:String){
            var journey : Journey = Journey(
                user = friend,
                timer = timer
            )
            GlobalJourneyList.add(journey)
            isJourneyDefined = true
        }
    }
}
