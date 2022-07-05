package com.komandor.komandor.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.komandor.komandor.data.database.model.Message
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.databinding.LiMessageBinding
import timber.log.Timber

class MessageViewHolder(val binding: LiMessageBinding) : RecyclerView.ViewHolder(binding.root) {
    private val TAG = MessageViewHolder::class.java.simpleName

    fun onBind(
        message: Message,
        user: User,

        onClick: (message: Message, position: Int) -> Unit,
    ) {
        //Timber.d("message = ${message}")

        if (user.pid == message.pid) {

            binding.cvReceived.visibility = View.GONE
            binding.cvSent.visibility = View.VISIBLE
            binding.cvSentMessage.tvMessage.text = message.text
            binding.cvSentMessage.tvDateMessage.text = message.statusTime.toString()
        } else {
            binding.cvReceived.visibility = View.VISIBLE
            binding.cvSent.visibility = View.GONE
            binding.cvReceivedMessage.tvUser.text = message.user

            binding.cvReceivedMessage.tvMessage.text = message.text
            binding.cvReceivedMessage.tvDateMessage.text = message.statusTime.toString()

        }
        binding.root.setOnClickListener(View.OnClickListener {
            onClick(message, absoluteAdapterPosition)
        })
    }

}