package com.example.firebasechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasechat.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    lateinit var adapter: NewMessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        auth = Firebase.auth
//        setUpProfilePic()
        setContentView(binding.root)
        val database = Firebase.database
        val myRef = database.getReference("message")
        updateListener(myRef)
        initRcView()

        binding.buttonId.setOnClickListener{
            val message = binding.inputField.text.toString()
            if (message.isNotEmpty()) {
                myRef.child(myRef.push().key ?: "Error").setValue(
                    User(
                        auth.currentUser?.displayName,
                        binding.inputField.text.toString(),


                    )
                )
                updateListener(myRef)
                binding.inputField.text.clear()
            }
        }


    }

    private fun initRcView() = with(binding){
        adapter = NewMessageAdapter()
        messageField.layoutManager = LinearLayoutManager(this@MainActivity)
        messageField.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.sign_out){
            auth.signOut()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<User>()
                for (s in snapshot.children) {
                   val user = s.getValue(User::class.java)
                   if (user != null) {
                       list.add(user)
                   }
               }
                adapter.setMessagesList(list)
                messageField.smoothScrollToPosition(adapter.itemCount - 1)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MainActivity", "Failed to read value.", error.toException())
            }
        })
    }
//      Set Profile pic icon
//
//    private fun setUpProfilePic() {
//        val pic = supportActionBar
//        Thread{
//            val bMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
//            val icon = BitmapDrawable(resources, bMap)
//            runOnUiThread {
//                pic?.setDisplayHomeAsUpEnabled(true)
//                pic?.setHomeAsUpIndicator(icon)
//                pic?.title = auth.currentUser?.displayName
//            }
//        }.start()
//    }

}