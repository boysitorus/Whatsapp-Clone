package com.ifs21025.whatsappclone

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.ifs21025.whatsappclone.databinding.ListChatBinding

//class ChatAdapter (private val chatList : ArrayList<User>) :
//    RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.MyViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_chat,
//            parent, false)
//        return MyViewHolder(itemView)
//
//    }
//
//    override fun onBindViewHolder(holder: ChatAdapter.MyViewHolder, position: Int) {
//        val currentItem = chatList[position]
//        holder.ivProfileImg.setImageResource(currentItem.userProfile)
//        holder.tvUsername.text = currentItem.userName
//        holder.tvLastChat.text = currentItem.userLastChat
//        holder.tvLastTime.text = currentItem.userLastTime
//    }
//
//    override fun getItemCount(): Int {
//        return chatList.size
//    }
//
//    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//
//        val ivProfileImg : ShapeableImageView = itemView.findViewById(R.id.ivProfileUser)
//        val tvUsername : TextView = itemView.findViewById(R.id.tvUsername)
//        val tvLastChat : TextView = itemView.findViewById(R.id.tv_lastChat)
//        val tvLastTime : TextView = itemView.findViewById(R.id.tv_lastTime)
//
//    }
//}

class ChatAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<ChatAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {  val binding =
        ListChatBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val user = listUser[position]
        holder.binding.ivProfileUser.setImageResource(user.userProfile)
        holder.binding.tvUsername.text = user.userName
        holder.binding.tvLastChat.text = user.userLastChat
        holder.binding.tvLastTime.text = user.userLastTime
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUser.size

    class ListViewHolder(var binding: ListChatBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
} 