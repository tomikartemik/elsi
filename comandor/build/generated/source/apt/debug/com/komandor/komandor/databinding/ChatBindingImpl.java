package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ChatBindingImpl extends ChatBinding implements com.komandor.komandor.generated.callback.OnClickListener.Listener {

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
    private final android.widget.FrameLayout mboundView1;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.LinearLayout mboundView4;
    @NonNull
    private final android.widget.ImageButton mboundView5;
    @NonNull
    private final android.widget.ImageButton mboundView7;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback10;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener etChatMessageInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.messageText.getValue()
            //         is vm.messageText.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etChatMessageInput);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm.messageText.getValue()
            java.lang.String vmMessageTextGetValue = null;
            // vm
            com.komandor.komandor.ui.chat.ChatViewModel vm = mVm;
            // vm.messageText
            androidx.lifecycle.MutableLiveData<java.lang.String> vmMessageText = null;
            // vm.messageText != null
            boolean vmMessageTextJavaLangObjectNull = false;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {


                vmMessageText = vm.getMessageText();

                vmMessageTextJavaLangObjectNull = (vmMessageText) != (null);
                if (vmMessageTextJavaLangObjectNull) {




                    vmMessageText.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public ChatBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private ChatBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.EditText) bindings[6]
            , (androidx.recyclerview.widget.RecyclerView) bindings[2]
            );
        this.etChatMessageInput.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.FrameLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.LinearLayout) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.ImageButton) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView7 = (android.widget.ImageButton) bindings[7];
        this.mboundView7.setTag(null);
        this.rvChatMessagesList.setTag(null);
        setRootTag(root);
        // listeners
        mCallback10 = new com.komandor.komandor.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
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
        if (BR.vm == variableId) {
            setVm((com.komandor.komandor.ui.chat.ChatViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable com.komandor.komandor.ui.chat.ChatViewModel Vm) {
        this.mVm = Vm;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.vm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeVmMessages((androidx.lifecycle.LiveData<androidx.paging.PagedList<com.komandor.komandor.data.database.messages.Message>>) object, fieldId);
            case 1 :
                return onChangeVmMessageText((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeVmIsLoading((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVmMessages(androidx.lifecycle.LiveData<androidx.paging.PagedList<com.komandor.komandor.data.database.messages.Message>> VmMessages, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmMessageText(androidx.lifecycle.MutableLiveData<java.lang.String> VmMessageText, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmIsLoading(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmIsLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
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
        boolean vmMessagesSizeInt0 = false;
        int vmMessagesSizeInt0VVISIBLEVGONE = 0;
        com.komandor.komandor.ui.chat.ChatViewModel vm = mVm;
        java.lang.String vmMessageTextGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = false;
        int vmIsLoadingVGONEVVISIBLE = 0;
        int vmMessagesSize = 0;
        androidx.lifecycle.LiveData<androidx.paging.PagedList<com.komandor.komandor.data.database.messages.Message>> vmMessages = null;
        com.komandor.komandor.ui.chat.MessagesAdapter.OnMessageItemClickListener vmOnMessageItemClickListener = null;
        int vmIsLoadingVVISIBLEVGONE = 0;
        androidx.paging.PagedList<com.komandor.komandor.data.database.messages.Message> vmMessagesGetValue = null;
        android.view.View.OnClickListener vmOnSendFileClickListener = null;
        java.lang.Boolean vmIsLoadingGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> vmMessageText = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsLoading = null;
        com.komandor.komandor.ui.chat.MessagesAdapter.OnDownloadFileClickListener vmOnDownloadFileClickListener = null;
        int vmMessagesSizeInt0VGONEVVISIBLE = 0;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (vm != null) {
                        // read vm.messages
                        vmMessages = vm.getMessages();
                        // read vm.onMessageItemClickListener
                        vmOnMessageItemClickListener = vm.getOnMessageItemClickListener();
                        // read vm.onDownloadFileClickListener
                        vmOnDownloadFileClickListener = vm.getOnDownloadFileClickListener();
                    }
                    updateLiveDataRegistration(0, vmMessages);


                    if (vmMessages != null) {
                        // read vm.messages.getValue()
                        vmMessagesGetValue = vmMessages.getValue();
                    }


                    if (vmMessagesGetValue != null) {
                        // read vm.messages.getValue().size()
                        vmMessagesSize = vmMessagesGetValue.size();
                    }


                    // read vm.messages.getValue().size() > 0
                    vmMessagesSizeInt0 = (vmMessagesSize) > (0);
                if((dirtyFlags & 0x19L) != 0) {
                    if(vmMessagesSizeInt0) {
                            dirtyFlags |= 0x40L;
                            dirtyFlags |= 0x1000L;
                    }
                    else {
                            dirtyFlags |= 0x20L;
                            dirtyFlags |= 0x800L;
                    }
                }


                    // read vm.messages.getValue().size() > 0 ? V.VISIBLE : V.GONE
                    vmMessagesSizeInt0VVISIBLEVGONE = ((vmMessagesSizeInt0) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                    // read vm.messages.getValue().size() > 0 ? V.GONE : V.VISIBLE
                    vmMessagesSizeInt0VGONEVVISIBLE = ((vmMessagesSizeInt0) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
            }
            if ((dirtyFlags & 0x18L) != 0) {

                    if (vm != null) {
                        // read vm.onSendFileClickListener
                        vmOnSendFileClickListener = vm.getOnSendFileClickListener();
                    }
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (vm != null) {
                        // read vm.messageText
                        vmMessageText = vm.getMessageText();
                    }
                    updateLiveDataRegistration(1, vmMessageText);


                    if (vmMessageText != null) {
                        // read vm.messageText.getValue()
                        vmMessageTextGetValue = vmMessageText.getValue();
                    }
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (vm != null) {
                        // read vm.isLoading
                        vmIsLoading = vm.getIsLoading();
                    }
                    updateLiveDataRegistration(2, vmIsLoading);


                    if (vmIsLoading != null) {
                        // read vm.isLoading.getValue()
                        vmIsLoadingGetValue = vmIsLoading.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmIsLoadingGetValue);
                if((dirtyFlags & 0x1cL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) {
                            dirtyFlags |= 0x100L;
                            dirtyFlags |= 0x400L;
                    }
                    else {
                            dirtyFlags |= 0x80L;
                            dirtyFlags |= 0x200L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.GONE : V.VISIBLE
                    vmIsLoadingVGONEVVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
                    vmIsLoadingVVISIBLEVGONE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etChatMessageInput, vmMessageTextGetValue);
        }
        if ((dirtyFlags & 0x10L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etChatMessageInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etChatMessageInputandroidTextAttrChanged);
            this.mboundView7.setOnClickListener(mCallback10);
        }
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            this.mboundView1.setVisibility(vmIsLoadingVGONEVVISIBLE);
            this.mboundView4.setVisibility(vmIsLoadingVVISIBLEVGONE);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            this.mboundView3.setVisibility(vmMessagesSizeInt0VGONEVVISIBLE);
            this.rvChatMessagesList.setVisibility(vmMessagesSizeInt0VVISIBLEVGONE);
            com.komandor.komandor.widgets.binding_adapters.RecyclerViewBindingAdapter.configureRecyclerView(this.rvChatMessagesList, vmMessagesGetValue, vmOnMessageItemClickListener, vmOnDownloadFileClickListener);
        }
        if ((dirtyFlags & 0x18L) != 0) {
            // api target 1

            this.mboundView5.setOnClickListener(vmOnSendFileClickListener);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // vm != null
        boolean vmJavaLangObjectNull = false;
        // vm
        com.komandor.komandor.ui.chat.ChatViewModel vm = mVm;



        vmJavaLangObjectNull = (vm) != (null);
        if (vmJavaLangObjectNull) {


            vm.sendMessage();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm.messages
        flag 1 (0x2L): vm.messageText
        flag 2 (0x3L): vm.isLoading
        flag 3 (0x4L): vm
        flag 4 (0x5L): null
        flag 5 (0x6L): vm.messages.getValue().size() > 0 ? V.VISIBLE : V.GONE
        flag 6 (0x7L): vm.messages.getValue().size() > 0 ? V.VISIBLE : V.GONE
        flag 7 (0x8L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.GONE : V.VISIBLE
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.GONE : V.VISIBLE
        flag 9 (0xaL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 11 (0xcL): vm.messages.getValue().size() > 0 ? V.GONE : V.VISIBLE
        flag 12 (0xdL): vm.messages.getValue().size() > 0 ? V.GONE : V.VISIBLE
    flag mapping end*/
    //end
}