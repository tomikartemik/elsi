package com.komandor.komandor.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.komandor.komandor.data.database.model.Chat
import com.komandor.komandor.databinding.LiChatBinding
import com.komandor.komandor.extension.load

class ChatsViewHolder(val binding: LiChatBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        chat: Chat,
        onClick: (chat: Chat, position: Int) -> Unit,
    ) {
        binding.chatsFragmentAvatar.load(chat.profilePhoto)
        binding.chatsListItemName.text = "${chat.status} ${chat.chatType} ${chat.chatName}"
        if (chat.profileName.isNullOrEmpty()){
            binding.chatsListItemprofileName.visibility = View.GONE
        } else {
            binding.chatsListItemprofileName.visibility = View.VISIBLE
            binding.chatsListItemprofileName.text = chat.profileName
        }
        if (chat.profileTitle.isNullOrEmpty()){
            binding.chatsListItemprofileTitle.visibility = View.GONE
        } else {
            binding.chatsListItemprofileTitle.visibility = View.VISIBLE
            binding.chatsListItemprofileTitle.text = chat.profileTitle
        }

        if (chat.profileCompany.isNullOrEmpty()) {
            binding.chatsListItemprofileCompany.visibility = View.GONE
        } else{
            binding.chatsListItemprofileCompany.visibility = View.VISIBLE
            binding.chatsListItemprofileCompany.text = chat.profileCompany

        }

        binding.chatsListItemLastMessage.text = "${chat.lastMessageStatus} ${chat.lastMessageText}"

        if (chat.unreadMessages>0) {
            binding.chatsListItemBadge.visibility = View.VISIBLE
            binding.chatsListItemBadge.text = chat.unreadMessages.toString()
        } else {
            binding.chatsListItemBadge.visibility = View.GONE
        }

        binding.root.setOnClickListener(View.OnClickListener {
            onClick(chat, absoluteAdapterPosition)
        })
    }

}