package com.komandor.komandor.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.komandor.komandor.R
import com.komandor.komandor.data.database.model.Chat
import com.komandor.komandor.databinding.LiChatBinding
import com.komandor.komandor.ui.base.BaseAdapter

class ChatsAdapter(
    dataList: List<Chat>,
    val onClick: (certificate: Chat, position: Int) -> Unit,
) : BaseAdapter<Chat, ChatsViewHolder>() {
    val TAG = ChatsAdapter::class.java.simpleName


    init {
        //L.d(TAG, "menuListItems = " +menuListItems)
        setList(dataList)
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val data = dataList[position]

        when (holder) {
            is ChatsViewHolder -> {
                holder.onBind(data, onClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size // one more to add header row
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.li_chat
    }

    override fun createViewHolder(
        ctx: Context,
        parent: ViewGroup?,
        viewType: Int
    ): ChatsViewHolder {
        val binding = LiChatBinding
            .inflate(LayoutInflater.from(ctx), parent, false)
        return ChatsViewHolder(binding)
        //return OrderViewHolder(view)
    }
}