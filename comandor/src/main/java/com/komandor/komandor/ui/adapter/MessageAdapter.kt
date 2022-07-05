package com.komandor.komandor.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.komandor.komandor.R
import com.komandor.komandor.data.database.model.Message
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.databinding.LiMessageBinding
import com.komandor.komandor.ui.base.BaseAdapter

class MessageAdapter(
    dataList: List<Message>,
    val user: User,
    val onClick: (message: Message, position: Int) -> Unit,
) : BaseAdapter<Message, MessageViewHolder>() {
    val TAG = MessageAdapter::class.java.simpleName


    init {
        //L.d(TAG, "menuListItems = " +menuListItems)
        setList(dataList)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val data = dataList[position]

        when (holder) {
            is MessageViewHolder -> {
                holder.onBind(data, user, onClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size // one more to add header row
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.li_message
    }

    override fun createViewHolder(
        ctx: Context,
        parent: ViewGroup?,
        viewType: Int
    ): MessageViewHolder {
        val binding = LiMessageBinding
            .inflate(LayoutInflater.from(ctx), parent, false)
        return MessageViewHolder(binding)
        //return OrderViewHolder(view)
    }
}