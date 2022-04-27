package com.example.firebasechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.firebasechat.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = Firebase.database
        val myRef = database.getReference("message")
        binding.buttonId.setOnClickListener {
            myRef.setValue(binding.inputField.text.toString())
            updateListener(myRef)
            binding.inputField.text.clear()
        }

    }

    private fun updateListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               binding.apply {
                   TextViewId.append("\n")
                   TextViewId.append(snapshot.value.toString())
               }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}