package com.example.firebasechat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasechat.databinding.UserListItemBinding


class NewMessageAdapter:RecyclerView.Adapter<NewMessageAdapter.ItemHolder>() {

    private var messagesList:List<User> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindingMain = UserListItemBinding.inflate(inflater, parent, false)
//        val bindingSecondary = UserListItemSecondaryBinding.inflate(inflater, parent, false)
        return ItemHolder(bindingMain)

    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(messagesList[position])
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    class ItemHolder(private val binding: UserListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            binding.message.text = user.message
            binding.userName.text = user.name
        }

    }


    fun setMessagesList(messagesList: List<User>){
        this.messagesList = messagesList
        notifyDataSetChanged()
    }
}

