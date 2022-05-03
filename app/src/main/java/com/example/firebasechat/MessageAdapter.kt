package com.example.firebasechat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasechat.databinding.UserListItemBinding
import java.text.DateFormat


class MessageAdapter : ListAdapter<User, MessageAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(private val binding: UserListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User) = with(binding){
            message.text = user.message
            userName.text = user.name
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
