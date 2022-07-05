package com.komandor.komandor.ui.fragments

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.komandor.komandor.R
import com.komandor.komandor.data.database.model.Chat
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.databinding.FrChatsBinding
import com.komandor.komandor.ui.adapter.ChatsAdapter
import com.komandor.komandor.ui.base.BaseFragment
import com.komandor.komandor.ui.base.viewBinding
import com.komandor.komandor.ui.viewmodel.ChatsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatsFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fr_chats


    private val viewModel by viewModels<ChatsViewModel>()
    private val binding by viewBinding<FrChatsBinding>()
    private var chatAdapter: ChatsAdapter? = null

    override fun initUI() {
        initAdapter()

        initStateWatcher(viewModel.state)
        viewModel.getProfile()
    }

    override fun onComplete(data: Any?) {
        if (data != null) {
            if (data is User) {
                viewModel.getChats()
            } else {
                lifecycle.coroutineScope.launch {
                    viewModel.allChats().collect {
                        chatAdapter?.setList(it)
                    }
                }

            }
        }
    }


    private fun initAdapter() {
        chatAdapter = ChatsAdapter(
            emptyList(),
            onClick = { coupon, position -> onItemClick(coupon, position) },
        )

        with(binding.rvList) {
            layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
            val dividerItemDecoration = DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )

            dividerItemDecoration.setDrawable(
                resources.getDrawable(
                    R.drawable.divider_drawable,
                    null
                )
            )
            //addItemDecoration(HItemDecoration(16))
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
            adapter = chatAdapter
        }
    }

    private fun onItemClick(chat: Chat, position: Int) {

        val args = Bundle()
        args.putString("chatId", chat.id)
        toScreen(R.id.messageListFragment, args)
    }

    override fun back() {
        requireActivity().finish()
    }
}