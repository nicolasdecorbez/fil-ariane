package com.example.ardianethread.Globals

import android.app.Application
import com.example.ardianethread.Data.Journey
import com.example.ardianethread.Data.Messages
import com.example.ardianethread.Data.Users

class Global : Application() {
    //SETUP FOR CURRENT USER//
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
        var isLoged : Boolean = false
    }
    //SETUP FOR USER CORRESPONDING IN MESSAGE ACTIVITY
    object Corres {
       var name = ""
   }
    // SETUP  AND FUNCTIONS FOR JOURNEYS//
    object CurrentJourneys {
        var GlobalJourneyList: ArrayList<Journey> = arrayListOf()
        var isJourneyDefined = false
        var timestamp = "Not initalized"
        var currentJourneyTimer : Long = 0
        var theseusJourney : Journey = Journey(
            user = "Unknown",
            timer = "Unknown"
        )
        var arrayId : ArrayList<Int> = arrayListOf()
        var iterator = 0
        var defined = false

        //ADD A JOURNEY IN THE CURRENT ARRAY OF JOURNEYS FOR ARDIANE USER
        fun inflateJourneyInfo(friend: String, timer: String) {
            var journey: Journey = Journey(
                user = friend,
                timer = timer
            )
            GlobalJourneyList.add(journey)
            isJourneyDefined = true
        }
        fun findJourneyinArray(user : String): Int {
            var position = -1
            for (i in GlobalJourneyList.indices)
            {
                if(GlobalJourneyList[i].user == user){
                    position = i
                }
            }
            return position
        }
        fun deleteJourneyEntry(user : String){
                for (i in GlobalJourneyList.indices)
                {
                    if(GlobalJourneyList[i].user == user){
                        GlobalJourneyList.remove(GlobalJourneyList[i])
                    }
                }
        }

        fun updateJourneyEntry(username : String,timer: String){
            for(i in GlobalJourneyList.indices){
                if (GlobalJourneyList[i].user == username) {
                    GlobalJourneyList[i].timer = timer
                }
            }
        }
    }
    object TempFriendUser{
        var Users: Users = Users(
            name = "0",
            firstname = "0",
            phone = "0",
            email = "0",
            blood_type = "0",
            hid = "0"
        )
        var idDefined = false
        var arrayDefined = false
        var iterator = 0
        var Ids : ArrayList<Int> = arrayListOf()
        var UsersArray : ArrayList<Users> = arrayListOf()
    }
    object TempMessageUser{
        var Users: Users = Users(
            name = "0",
            firstname = "0",
            phone = "0",
            email = "0",
            blood_type = "0",
            hid = "0"
        )
        var idDefined = false
        var arrayDefined = false
        var iterator = 0
        var Ids : ArrayList<Int> = arrayListOf()
        var UsersArray : ArrayList<Users> = arrayListOf()
    }
    object BuiltConversation{
        var FriendMessageArray : ArrayList<Messages> = arrayListOf()
        var MyMessageArray: ArrayList<Messages> = arrayListOf()
        var ConversationArray: ArrayList<Messages> = arrayListOf()
        var FriendId  = -1

        var initFriendArray = false
        var initMyArray = false
    }
}
