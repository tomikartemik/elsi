package com.komandor.komandor.widgets.binding_adapters;

import com.komandor.komandor.data.database.chats.ChatWithLastMessage;
import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.ui.chat.MessagesAdapter;
import com.komandor.komandor.ui.main.chats.ChatsAdapter;
import com.komandor.komandor.ui.main.contacts.ContactsAdapter;
import com.komandor.komandor.widgets.MarginItemDecoration;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewBindingAdapter {
  @BindingAdapter({"bind:data", "bind:clickHandler"})
  public static void configureRecyclerView(RecyclerView recyclerView, List<Contact> contacts, ContactsAdapter.OnContactItemClickListener listener) {
    ContactsAdapter adapter = new ContactsAdapter(listener);
    adapter.submitList(contacts);
    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    recyclerView.setAdapter(adapter);
  }
  
  @BindingAdapter({"bind:data", "bind:clickHandler"})
  public static void configureRecyclerView(RecyclerView recyclerView, List<ChatWithLastMessage> chats, ChatsAdapter.OnChatItemClickListener listener) {
    ChatsAdapter adapter = new ChatsAdapter(listener);
    adapter.submitList(chats);
    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    recyclerView.setAdapter(adapter);
  }
  
  @BindingAdapter({"bind:data", "bind:clickHandler", "bind:downloadFileClickHandler"})
  public static void configureRecyclerView(RecyclerView recyclerView,
                                           PagedList<Message> messages,
                                           MessagesAdapter.OnMessageItemClickListener onMessageItemClickListener,
                                           MessagesAdapter.OnDownloadFileClickListener onDownloadFileClickListener) {
    MessagesAdapter adapter = new MessagesAdapter(onMessageItemClickListener, onDownloadFileClickListener);
    adapter.submitList(messages);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
    linearLayoutManager.setReverseLayout(true);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(adapter);
    recyclerView.scrollToPosition(0);
  }
}
