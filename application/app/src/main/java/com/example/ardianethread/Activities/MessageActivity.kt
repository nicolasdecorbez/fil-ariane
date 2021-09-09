package com.example.ardianethread.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Adapters.ContactAdapter
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R

class MessageActivity : AppCompatActivity() {
    private lateinit var friendView: RecyclerView
    private lateinit var friendList: ArrayList<Users>
    lateinit var friendName : Array<String>
    lateinit var friendNumber : Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        if (!Global.Current.isLoged){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            true
        }

        setSupportActionBar(findViewById(R.id.aToolbar))
        title = ""

        friendView= findViewById<RecyclerView>(R.id.message_view)
        friendView.layoutManager = LinearLayoutManager(this)
        friendView.setHasFixedSize(true)

        /*friendName = arrayOf(
            "Nico",
            "Kevin",
            "Lucas"
        )

        friendNumber = arrayOf(
            "06.00.00.00.01",
            "06.00.00.00.02",
            "06.00.00.00.03"
        )*/

        friendList =  Global.TempFriendUser.UsersArray

        var adapter = ContactAdapter(friendList)
        friendView.adapter = adapter
        adapter.setOnItemClickListener(object : ContactAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Global.Corres.name = friendList[position].firstname
                val fragmentManager = supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                    transaction.setReorderingAllowed(true)
                    transaction.replace(R.id.friend_layout, fragment_conversation())
                    transaction.commit()
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_menu,menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.home_button -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.subitem_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.subitem_alarm -> {
                val intent = Intent(this, AlarmActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.contact_button ->{
                val intent = Intent(this, FriendActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.message_button ->{
                val intent = Intent(this, MessageActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.subitem_Login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.subitem_register -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.subitem_notif -> {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}