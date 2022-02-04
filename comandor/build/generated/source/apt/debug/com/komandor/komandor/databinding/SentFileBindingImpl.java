package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SentFileBindingImpl extends SentFileBinding implements com.komandor.komandor.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.ImageView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.LinearLayout mboundView3;
    @NonNull
    private final com.google.android.material.floatingactionbutton.FloatingActionButton mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback15;
    @Nullable
    private final android.view.View.OnClickListener mCallback14;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SentFileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private SentFileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.LinearLayout) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        setRootTag(root);
        // listeners
        mCallback15 = new com.komandor.komandor.generated.callback.OnClickListener(this, 2);
        mCallback14 = new com.komandor.komandor.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
        if (BR.onMessageItemClickListener == variableId) {
            setOnMessageItemClickListener((com.komandor.komandor.ui.chat.MessagesAdapter.OnMessageItemClickListener) variable);
        }
        else if (BR.onDownloadFileClickListener == variableId) {
            setOnDownloadFileClickListener((com.komandor.komandor.ui.chat.MessagesAdapter.OnDownloadFileClickListener) variable);
        }
        else if (BR.message == variableId) {
            setMessage((com.komandor.komandor.ui.chat.MessageItemViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setOnMessageItemClickListener(@Nullable com.komandor.komandor.ui.chat.MessagesAdapter.OnMessageItemClickListener OnMessageItemClickListener) {
        this.mOnMessageItemClickListener = OnMessageItemClickListener;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.onMessageItemClickListener);
        super.requestRebind();
    }
    public void setOnDownloadFileClickListener(@Nullable com.komandor.komandor.ui.chat.MessagesAdapter.OnDownloadFileClickListener OnDownloadFileClickListener) {
        this.mOnDownloadFileClickListener = OnDownloadFileClickListener;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.onDownloadFileClickListener);
        super.requestRebind();
    }
    public void setMessage(@Nullable com.komandor.komandor.ui.chat.MessageItemViewModel Message) {
        this.mMessage = Message;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.message);
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
        boolean messageIsSent = false;
        boolean messageIsGrouped = false;
        java.lang.String messageMessage = null;
        com.komandor.komandor.ui.chat.MessagesAdapter.OnMessageItemClickListener onMessageItemClickListener = mOnMessageItemClickListener;
        com.komandor.komandor.ui.chat.MessagesAdapter.OnDownloadFileClickListener onDownloadFileClickListener = mOnDownloadFileClickListener;
        java.lang.String messageDate = null;
        com.komandor.komandor.ui.chat.MessageItemViewModel message = mMessage;
        android.graphics.drawable.Drawable messageIsGroupedMboundView3AndroidDrawableSentMessageGroupMboundView3AndroidDrawableSentMessage = null;
        int messageIsSentVINVISIBLEVVISIBLE = 0;

        if ((dirtyFlags & 0xcL) != 0) {



                if (message != null) {
                    // read message.isSent
                    messageIsSent = message.isSent();
                    // read message.isGrouped
                    messageIsGrouped = message.isGrouped();
                    // read message.message
                    messageMessage = message.getMessage();
                    // read message.date
                    messageDate = message.getDate();
                }
            if((dirtyFlags & 0xcL) != 0) {
                if(messageIsSent) {
                        dirtyFlags |= 0x80L;
                }
                else {
                        dirtyFlags |= 0x40L;
                }
            }
            if((dirtyFlags & 0xcL) != 0) {
                if(messageIsGrouped) {
                        dirtyFlags |= 0x20L;
                }
                else {
                        dirtyFlags |= 0x10L;
                }
            }


                // read message.isSent ? V.INVISIBLE : V.VISIBLE
                messageIsSentVINVISIBLEVVISIBLE = ((messageIsSent) ? (android.view.View.INVISIBLE) : (android.view.View.VISIBLE));
                // read message.isGrouped ? @android:drawable/sent_message_group : @android:drawable/sent_message
                messageIsGroupedMboundView3AndroidDrawableSentMessageGroupMboundView3AndroidDrawableSentMessage = ((messageIsGrouped) ? (getDrawableFromResource(mboundView3, R.drawable.sent_message_group)) : (getDrawableFromResource(mboundView3, R.drawable.sent_message)));
        }
        // batch finished
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(mCallback14);
            this.mboundView4.setOnClickListener(mCallback15);
        }
        if ((dirtyFlags & 0xcL) != 0) {
            // api target 1

            this.mboundView1.setVisibility(messageIsSentVINVISIBLEVVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, messageDate);
            com.komandor.komandor.widgets.binding_adapters.LinearLayoutBindingAdapter.configureLinearLayout(this.mboundView3, messageIsGroupedMboundView3AndroidDrawableSentMessageGroupMboundView3AndroidDrawableSentMessage);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, messageMessage);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // message != null
                boolean messageJavaLangObjectNull = false;
                // onDownloadFileClickListener
                com.komandor.komandor.ui.chat.MessagesAdapter.OnDownloadFileClickListener onDownloadFileClickListener = mOnDownloadFileClickListener;
                // onDownloadFileClickListener != null
                boolean onDownloadFileClickListenerJavaLangObjectNull = false;
                // message
                com.komandor.komandor.ui.chat.MessageItemViewModel message = mMessage;
                // message.getLocalFileID()
                long messageGetLocalFileID = 0L;



                onDownloadFileClickListenerJavaLangObjectNull = (onDownloadFileClickListener) != (null);
                if (onDownloadFileClickListenerJavaLangObjectNull) {



                    messageJavaLangObjectNull = (message) != (null);
                    if (messageJavaLangObjectNull) {


                        messageGetLocalFileID = message.getLocalFileID();

                        onDownloadFileClickListener.onDownloadFileClick(messageGetLocalFileID);
                    }
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // message.id
                long messageId = 0L;
                // onMessageItemClickListener != null
                boolean onMessageItemClickListenerJavaLangObjectNull = false;
                // message != null
                boolean messageJavaLangObjectNull = false;
                // message.hasFile
                boolean messageHasFile = false;
                // message
                com.komandor.komandor.ui.chat.MessageItemViewModel message = mMessage;
                // onMessageItemClickListener
                com.komandor.komandor.ui.chat.MessagesAdapter.OnMessageItemClickListener onMessageItemClickListener = mOnMessageItemClickListener;



                onMessageItemClickListenerJavaLangObjectNull = (onMessageItemClickListener) != (null);
                if (onMessageItemClickListenerJavaLangObjectNull) {



                    messageJavaLangObjectNull = (message) != (null);
                    if (messageJavaLangObjectNull) {


                        messageId = message.getId();



                        messageHasFile = message.hasFile();

                        onMessageItemClickListener.onMessageItemClick(messageId, messageHasFile);
                    }
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): onMessageItemClickListener
        flag 1 (0x2L): onDownloadFileClickListener
        flag 2 (0x3L): message
        flag 3 (0x4L): null
        flag 4 (0x5L): message.isGrouped ? @android:drawable/sent_message_group : @android:drawable/sent_message
        flag 5 (0x6L): message.isGrouped ? @android:drawable/sent_message_group : @android:drawable/sent_message
        flag 6 (0x7L): message.isSent ? V.INVISIBLE : V.VISIBLE
        flag 7 (0x8L): message.isSent ? V.INVISIBLE : V.VISIBLE
    flag mapping end*/
    //end
}