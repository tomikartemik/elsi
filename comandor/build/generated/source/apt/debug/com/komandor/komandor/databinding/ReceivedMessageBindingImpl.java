package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ReceivedMessageBindingImpl extends ReceivedMessageBinding implements com.komandor.komandor.generated.callback.OnClickListener.Listener {

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
    private final android.widget.LinearLayout mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback7;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ReceivedMessageBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private ReceivedMessageBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[4]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.LinearLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.messageItemTime.setTag(null);
        setRootTag(root);
        // listeners
        mCallback7 = new com.komandor.komandor.generated.callback.OnClickListener(this, 1);
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
        if (BR.onMessageItemClickListener == variableId) {
            setOnMessageItemClickListener((com.komandor.komandor.ui.chat.MessagesAdapter.OnMessageItemClickListener) variable);
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
    public void setMessage(@Nullable com.komandor.komandor.ui.chat.MessageItemViewModel Message) {
        this.mMessage = Message;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
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
        boolean messageIsGrouped = false;
        java.lang.String messageMessage = null;
        com.komandor.komandor.ui.chat.MessagesAdapter.OnMessageItemClickListener onMessageItemClickListener = mOnMessageItemClickListener;
        java.lang.String messageDate = null;
        com.komandor.komandor.ui.chat.MessageItemViewModel message = mMessage;
        android.graphics.drawable.Drawable messageIsGroupedMboundView2AndroidDrawableReceivedMessageGroupMboundView2AndroidDrawableReceivedMessage = null;
        int messageIsGroupedVINVISIBLEVVISIBLE = 0;

        if ((dirtyFlags & 0x6L) != 0) {



                if (message != null) {
                    // read message.isGrouped
                    messageIsGrouped = message.isGrouped();
                    // read message.message
                    messageMessage = message.getMessage();
                    // read message.date
                    messageDate = message.getDate();
                }
            if((dirtyFlags & 0x6L) != 0) {
                if(messageIsGrouped) {
                        dirtyFlags |= 0x10L;
                        dirtyFlags |= 0x40L;
                }
                else {
                        dirtyFlags |= 0x8L;
                        dirtyFlags |= 0x20L;
                }
            }


                // read message.isGrouped ? @android:drawable/received_message_group : @android:drawable/received_message
                messageIsGroupedMboundView2AndroidDrawableReceivedMessageGroupMboundView2AndroidDrawableReceivedMessage = ((messageIsGrouped) ? (getDrawableFromResource(mboundView2, R.drawable.received_message_group)) : (getDrawableFromResource(mboundView2, R.drawable.received_message)));
                // read message.isGrouped ? V.INVISIBLE : V.VISIBLE
                messageIsGroupedVINVISIBLEVVISIBLE = ((messageIsGrouped) ? (android.view.View.INVISIBLE) : (android.view.View.VISIBLE));
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(mCallback7);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.mboundView1.setVisibility(messageIsGroupedVINVISIBLEVVISIBLE);
            com.komandor.komandor.widgets.binding_adapters.LinearLayoutBindingAdapter.configureLinearLayout(this.mboundView2, messageIsGroupedMboundView2AndroidDrawableReceivedMessageGroupMboundView2AndroidDrawableReceivedMessage);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, messageMessage);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.messageItemTime, messageDate);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
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
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): onMessageItemClickListener
        flag 1 (0x2L): message
        flag 2 (0x3L): null
        flag 3 (0x4L): message.isGrouped ? @android:drawable/received_message_group : @android:drawable/received_message
        flag 4 (0x5L): message.isGrouped ? @android:drawable/received_message_group : @android:drawable/received_message
        flag 5 (0x6L): message.isGrouped ? V.INVISIBLE : V.VISIBLE
        flag 6 (0x7L): message.isGrouped ? V.INVISIBLE : V.VISIBLE
    flag mapping end*/
    //end
}