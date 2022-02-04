package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ChatItemBindingImpl extends ChatItemBinding implements com.komandor.komandor.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.chatsFragmentAvatar, 4);
        sViewsWithIds.put(R.id.constraintLayout, 5);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback9;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ChatItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private ChatItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            , (android.widget.LinearLayout) bindings[5]
            );
        this.chatsListItemBadge.setTag(null);
        this.chatsListItemLastMessage.setTag(null);
        this.chatsListItemName.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback9 = new com.komandor.komandor.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.chat == variableId) {
            setChat((com.komandor.komandor.ui.main.chats.ChatItemViewModel) variable);
        }
        else if (BR.onItemClickListener == variableId) {
            setOnItemClickListener((com.komandor.komandor.ui.main.chats.ChatsAdapter.OnChatItemClickListener) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setChat(@Nullable com.komandor.komandor.ui.main.chats.ChatItemViewModel Chat) {
        this.mChat = Chat;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.chat);
        super.requestRebind();
    }
    public void setOnItemClickListener(@Nullable com.komandor.komandor.ui.main.chats.ChatsAdapter.OnChatItemClickListener OnItemClickListener) {
        this.mOnItemClickListener = OnItemClickListener;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.onItemClickListener);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        boolean chatUnreadInt0 = false;
        com.komandor.komandor.ui.main.chats.ChatItemViewModel chat = mChat;
        int chatUnread = 0;
        java.lang.String chatLastMessage = null;
        java.lang.String chatStringUnread = null;
        int chatUnreadInt0VVISIBLEVGONE = 0;
        java.lang.String chatName = null;
        com.komandor.komandor.ui.main.chats.ChatsAdapter.OnChatItemClickListener onItemClickListener = mOnItemClickListener;

        if ((dirtyFlags & 0x5L) != 0) {



                if (chat != null) {
                    // read chat.unread
                    chatUnread = chat.getUnread();
                    // read chat.lastMessage
                    chatLastMessage = chat.getLastMessage();
                    // read chat.stringUnread
                    chatStringUnread = chat.getStringUnread();
                    // read chat.name
                    chatName = chat.getName();
                }


                // read chat.unread > 0
                chatUnreadInt0 = (chatUnread) > (0);
            if((dirtyFlags & 0x5L) != 0) {
                if(chatUnreadInt0) {
                        dirtyFlags |= 0x10L;
                }
                else {
                        dirtyFlags |= 0x8L;
                }
            }


                // read chat.unread > 0 ? V.VISIBLE : V.GONE
                chatUnreadInt0VVISIBLEVGONE = ((chatUnreadInt0) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.chatsListItemBadge, chatStringUnread);
            this.chatsListItemBadge.setVisibility(chatUnreadInt0VVISIBLEVGONE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.chatsListItemLastMessage, chatLastMessage);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.chatsListItemName, chatName);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(mCallback9);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // chat
        com.komandor.komandor.ui.main.chats.ChatItemViewModel chat = mChat;
        // onItemClickListener
        com.komandor.komandor.ui.main.chats.ChatsAdapter.OnChatItemClickListener onItemClickListener = mOnItemClickListener;
        // chat.chatID
        java.lang.String chatChatID = null;
        // chat != null
        boolean chatJavaLangObjectNull = false;
        // onItemClickListener != null
        boolean onItemClickListenerJavaLangObjectNull = false;



        onItemClickListenerJavaLangObjectNull = (onItemClickListener) != (null);
        if (onItemClickListenerJavaLangObjectNull) {



            chatJavaLangObjectNull = (chat) != (null);
            if (chatJavaLangObjectNull) {


                chatChatID = chat.getChatID();

                onItemClickListener.onChatItemClick(chatChatID);
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): chat
        flag 1 (0x2L): onItemClickListener
        flag 2 (0x3L): null
        flag 3 (0x4L): chat.unread > 0 ? V.VISIBLE : V.GONE
        flag 4 (0x5L): chat.unread > 0 ? V.VISIBLE : V.GONE
    flag mapping end*/
    //end
}