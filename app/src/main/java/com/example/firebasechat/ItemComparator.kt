package com.example.firebasechat

import androidx.recyclerview.widget.DiffUtil

class ItemComparator : DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return  oldItem.message == newItem.message && oldItem.name == newItem.name

    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem

    }

}