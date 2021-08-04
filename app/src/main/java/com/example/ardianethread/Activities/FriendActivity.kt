package com.example.ardianethread.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Adapters.FriendAdapter
import com.example.ardianethread.Adapters.ToneAdapter
import com.example.ardianethread.Data.Tones
import com.example.ardianethread.Data.Users
import com.example.ardianethread.R

class FriendActivity : AppCompatActivity() {

    private lateinit var friendView: RecyclerView
    private lateinit var friendList: ArrayList<Users>
    lateinit var friendName : Array<String>
    lateinit var friendNumber : Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
        setSupportActionBar(findViewById(R.id.aToolbar))
        title = ""

        friendView= findViewById<RecyclerView>(R.id.friend_view)
        friendView.layoutManager = LinearLayoutManager(this)
        friendView.setHasFixedSize(true)

        friendName = arrayOf(
            "Nico",
            "Kevin",
            "Lucas"
        )

        friendNumber = arrayOf(
            "06.00.00.00.01",
            "06.00.00.00.02",
            "06.00.00.00.03"
        )

        friendList = arrayListOf<Users>()
        getFriendData()

        friendView.adapter = FriendAdapter(friendList)

        var add_friend_button : Button = findViewById(R.id.add_friend_button)
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        add_friend_button.setOnClickListener{
            transaction.setReorderingAllowed(true)
            transaction.replace(R.id.friend_layout, fragment_friend_add())
            transaction.commit()
        }
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
            R.id.contact_button ->{
                val intent = Intent(this, FriendActivity::class.java)
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
    private fun getFriendData() {
        for (i in friendName.indices) {
            val friend = Users(
                name = "unknown",
                firstname = friendName[i],
                phone = friendNumber[i],
                email = "unknown",
                blood_type = "unknown",
                hid = "unknown",
            )
            friendList.add(friend)
        }
    }
}