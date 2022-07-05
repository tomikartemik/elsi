package com.komandor.komandor.ui.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.komandor.komandor.R
import com.komandor.komandor.data.database.model.Chat
import com.komandor.komandor.data.database.model.Message
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.databinding.FrChatBinding
import com.komandor.komandor.ui.adapter.MarginItemDecoration
import com.komandor.komandor.ui.adapter.MessageAdapter
import com.komandor.komandor.ui.base.BaseFragment
import com.komandor.komandor.ui.base.viewBinding
import com.komandor.komandor.ui.viewmodel.MessageListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MessageListFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fr_chat


    private val viewModel by viewModels<MessageListViewModel>()
    private val binding by viewBinding<FrChatBinding>()

    private val args: MessageListFragmentArgs by navArgs()

    private var messageAdapter: MessageAdapter? = null

    override fun initUI() {

        initStateWatcher(viewModel.state)
        binding.tvEmptyList.visibility = View.GONE
        binding.rvList.visibility = View.GONE
        binding.btnAddFile.setOnClickListener {
            addFile()
        }

        binding.btnSendMessage.setOnClickListener {
            if (binding.etChatMessageInput.text.isNullOrEmpty()) {
                sendMessage(binding.etChatMessageInput.text.toString())
            }
        }
        viewModel.getMessages(args.chatId)
    }

    override fun onComplete(data: Any?) {
        if (data != null) {
            if (data is List<*>) {
                if (data.isEmpty() || viewModel.user==null) {
                    binding.tvEmptyList.visibility = View.VISIBLE
                    binding.rvList.visibility = View.GONE
                } else {
                    binding.tvEmptyList.visibility = View.GONE
                    binding.rvList.visibility = View.VISIBLE

                    initAdapter(viewModel.user!!)
                    lifecycle.coroutineScope.launch {
                        viewModel.allMessages().collect {
                            messageAdapter?.setList(it)
                        }
                    }
                }
            }
        }
    }

    private fun initAdapter(user: User) {
        messageAdapter = MessageAdapter(
            emptyList(),
            user,
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
            addItemDecoration(
                MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.message_margin))
            )
            adapter = messageAdapter
        }
    }

    private fun onItemClick(message: Message, position: Int) {
    }

    private fun addFile(){

    }

    private fun sendMessage(txt:String){
        viewModel.sendMessage(txt)
    }

}