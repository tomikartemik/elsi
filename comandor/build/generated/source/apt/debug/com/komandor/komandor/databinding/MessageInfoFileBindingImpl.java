package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class MessageInfoFileBindingImpl extends MessageInfoFileBinding implements com.komandor.komandor.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.messageInfoScreenButtonsLayout, 12);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    private final com.google.android.material.button.MaterialButton mboundView10;
    @NonNull
    private final android.widget.LinearLayout mboundView11;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    @NonNull
    private final com.google.android.material.button.MaterialButton mboundView7;
    @NonNull
    private final android.widget.FrameLayout mboundView8;
    @NonNull
    private final com.google.android.material.button.MaterialButton mboundView9;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback12;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public MessageInfoFileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private MessageInfoFileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 8
            , (android.widget.FrameLayout) bindings[12]
            , (android.widget.ProgressBar) bindings[6]
            );
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView10 = (com.google.android.material.button.MaterialButton) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (android.widget.LinearLayout) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView7 = (com.google.android.material.button.MaterialButton) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (android.widget.FrameLayout) bindings[8];
        this.mboundView8.setTag(null);
        this.mboundView9 = (com.google.android.material.button.MaterialButton) bindings[9];
        this.mboundView9.setTag(null);
        this.progressBar.setTag(null);
        setRootTag(root);
        // listeners
        mCallback12 = new com.komandor.komandor.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x200L;
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
            setVm((com.komandor.komandor.ui.message_info.MessageInfoViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable com.komandor.komandor.ui.message_info.MessageInfoViewModel Vm) {
        this.mVm = Vm;
        synchronized(this) {
            mDirtyFlags |= 0x100L;
        }
        notifyPropertyChanged(BR.vm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeVmHasFilePath((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeVmMessageDate((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeVmUserDetails((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeVmIsFileLoading((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 4 :
                return onChangeVmIsVerified((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 5 :
                return onChangeVmUserName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeVmIsLoading((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 7 :
                return onChangeVmMessage((androidx.lifecycle.MutableLiveData<com.komandor.komandor.data.database.messages.Message>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVmHasFilePath(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmHasFilePath, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmMessageDate(androidx.lifecycle.MutableLiveData<java.lang.String> VmMessageDate, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmUserDetails(androidx.lifecycle.MutableLiveData<java.lang.String> VmUserDetails, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmIsFileLoading(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmIsFileLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmIsVerified(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmIsVerified, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmUserName(androidx.lifecycle.MutableLiveData<java.lang.String> VmUserName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmIsLoading(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmIsLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmMessage(androidx.lifecycle.MutableLiveData<com.komandor.komandor.data.database.messages.Message> VmMessage, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
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
        java.lang.String vmMessageGetDecryptedData = null;
        com.komandor.komandor.ui.message_info.MessageInfoViewModel vm = mVm;
        java.lang.String vmUserNameGetValue = null;
        boolean vmIsVerifiedJavaLangObjectNull = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsVerifiedGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmHasFilePath = null;
        boolean vmUserDetailsEqualsJavaLangString = false;
        com.komandor.komandor.data.database.messages.Message vmMessageGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = false;
        int vmIsLoadingVGONEVVISIBLE = 0;
        java.lang.Boolean vmHasFilePathGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> vmMessageDate = null;
        int vmIsVerifiedVGONEVVISIBLE = 0;
        androidx.lifecycle.MutableLiveData<java.lang.String> vmUserDetails = null;
        int vmUserDetailsEqualsJavaLangStringVGONEVVISIBLE = 0;
        int vmIsVerifiedVVISIBLEVGONE = 0;
        int vmIsLoadingVVISIBLEVGONE = 0;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmHasFilePathGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsFileLoading = null;
        java.lang.Boolean vmIsLoadingGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsVerified = null;
        java.lang.Boolean vmIsFileLoadingGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> vmUserName = null;
        int vmIsFileLoadingVVISIBLEVGONE = 0;
        java.lang.String vmMessageDateGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsLoading = null;
        java.lang.String vmUserDetailsGetValue = null;
        androidx.lifecycle.MutableLiveData<com.komandor.komandor.data.database.messages.Message> vmMessage = null;
        int vmIsVerifiedJavaLangObjectNullVVISIBLEVGONE = 0;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsFileLoadingGetValue = false;
        int vmIsVerifiedJavaLangObjectNullVGONEVVISIBLE = 0;
        java.lang.Boolean vmIsVerifiedGetValue = null;

        if ((dirtyFlags & 0x3ffL) != 0) {


            if ((dirtyFlags & 0x301L) != 0) {

                    if (vm != null) {
                        // read vm.hasFilePath
                        vmHasFilePath = vm.getHasFilePath();
                    }
                    updateLiveDataRegistration(0, vmHasFilePath);


                    if (vmHasFilePath != null) {
                        // read vm.hasFilePath.getValue()
                        vmHasFilePathGetValue = vmHasFilePath.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.hasFilePath.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmHasFilePathGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmHasFilePathGetValue);
            }
            if ((dirtyFlags & 0x302L) != 0) {

                    if (vm != null) {
                        // read vm.messageDate
                        vmMessageDate = vm.getMessageDate();
                    }
                    updateLiveDataRegistration(1, vmMessageDate);


                    if (vmMessageDate != null) {
                        // read vm.messageDate.getValue()
                        vmMessageDateGetValue = vmMessageDate.getValue();
                    }
            }
            if ((dirtyFlags & 0x304L) != 0) {

                    if (vm != null) {
                        // read vm.userDetails
                        vmUserDetails = vm.getUserDetails();
                    }
                    updateLiveDataRegistration(2, vmUserDetails);


                    if (vmUserDetails != null) {
                        // read vm.userDetails.getValue()
                        vmUserDetailsGetValue = vmUserDetails.getValue();
                    }


                    if (vmUserDetailsGetValue != null) {
                        // read vm.userDetails.getValue().equals("")
                        vmUserDetailsEqualsJavaLangString = vmUserDetailsGetValue.equals("");
                    }
                if((dirtyFlags & 0x304L) != 0) {
                    if(vmUserDetailsEqualsJavaLangString) {
                            dirtyFlags |= 0x8000L;
                    }
                    else {
                            dirtyFlags |= 0x4000L;
                    }
                }


                    // read vm.userDetails.getValue().equals("") ? V.GONE : V.VISIBLE
                    vmUserDetailsEqualsJavaLangStringVGONEVVISIBLE = ((vmUserDetailsEqualsJavaLangString) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
            }
            if ((dirtyFlags & 0x308L) != 0) {

                    if (vm != null) {
                        // read vm.isFileLoading
                        vmIsFileLoading = vm.getIsFileLoading();
                    }
                    updateLiveDataRegistration(3, vmIsFileLoading);


                    if (vmIsFileLoading != null) {
                        // read vm.isFileLoading.getValue()
                        vmIsFileLoadingGetValue = vmIsFileLoading.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isFileLoading.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmIsFileLoadingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmIsFileLoadingGetValue);
                if((dirtyFlags & 0x308L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxVmIsFileLoadingGetValue) {
                            dirtyFlags |= 0x200000L;
                    }
                    else {
                            dirtyFlags |= 0x100000L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isFileLoading.getValue()) ? V.VISIBLE : V.GONE
                    vmIsFileLoadingVVISIBLEVGONE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsFileLoadingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x310L) != 0) {

                    if (vm != null) {
                        // read vm.isVerified
                        vmIsVerified = vm.getIsVerified();
                    }
                    updateLiveDataRegistration(4, vmIsVerified);


                    if (vmIsVerified != null) {
                        // read vm.isVerified.getValue()
                        vmIsVerifiedGetValue = vmIsVerified.getValue();
                    }


                    // read vm.isVerified.getValue() == null
                    vmIsVerifiedJavaLangObjectNull = (vmIsVerifiedGetValue) == (null);
                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isVerified.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmIsVerifiedGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmIsVerifiedGetValue);
                if((dirtyFlags & 0x310L) != 0) {
                    if(vmIsVerifiedJavaLangObjectNull) {
                            dirtyFlags |= 0x800000L;
                            dirtyFlags |= 0x2000000L;
                    }
                    else {
                            dirtyFlags |= 0x400000L;
                            dirtyFlags |= 0x1000000L;
                    }
                }
                if((dirtyFlags & 0x310L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxVmIsVerifiedGetValue) {
                            dirtyFlags |= 0x2000L;
                            dirtyFlags |= 0x20000L;
                    }
                    else {
                            dirtyFlags |= 0x1000L;
                            dirtyFlags |= 0x10000L;
                    }
                }


                    // read vm.isVerified.getValue() == null ? V.VISIBLE : V.GONE
                    vmIsVerifiedJavaLangObjectNullVVISIBLEVGONE = ((vmIsVerifiedJavaLangObjectNull) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                    // read vm.isVerified.getValue() == null ? V.GONE : V.VISIBLE
                    vmIsVerifiedJavaLangObjectNullVGONEVVISIBLE = ((vmIsVerifiedJavaLangObjectNull) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isVerified.getValue()) ? V.GONE : V.VISIBLE
                    vmIsVerifiedVGONEVVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsVerifiedGetValue) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isVerified.getValue()) ? V.VISIBLE : V.GONE
                    vmIsVerifiedVVISIBLEVGONE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsVerifiedGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x320L) != 0) {

                    if (vm != null) {
                        // read vm.userName
                        vmUserName = vm.getUserName();
                    }
                    updateLiveDataRegistration(5, vmUserName);


                    if (vmUserName != null) {
                        // read vm.userName.getValue()
                        vmUserNameGetValue = vmUserName.getValue();
                    }
            }
            if ((dirtyFlags & 0x340L) != 0) {

                    if (vm != null) {
                        // read vm.isLoading
                        vmIsLoading = vm.getIsLoading();
                    }
                    updateLiveDataRegistration(6, vmIsLoading);


                    if (vmIsLoading != null) {
                        // read vm.isLoading.getValue()
                        vmIsLoadingGetValue = vmIsLoading.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmIsLoadingGetValue);
                if((dirtyFlags & 0x340L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) {
                            dirtyFlags |= 0x800L;
                            dirtyFlags |= 0x80000L;
                    }
                    else {
                            dirtyFlags |= 0x400L;
                            dirtyFlags |= 0x40000L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.GONE : V.VISIBLE
                    vmIsLoadingVGONEVVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
                    vmIsLoadingVVISIBLEVGONE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x380L) != 0) {

                    if (vm != null) {
                        // read vm.message
                        vmMessage = vm.getMessage();
                    }
                    updateLiveDataRegistration(7, vmMessage);


                    if (vmMessage != null) {
                        // read vm.message.getValue()
                        vmMessageGetValue = vmMessage.getValue();
                    }


                    if (vmMessageGetValue != null) {
                        // read vm.message.getValue().getDecryptedData()
                        vmMessageGetDecryptedData = vmMessageGetValue.getDecryptedData();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x340L) != 0) {
            // api target 1

            this.mboundView1.setVisibility(vmIsLoadingVGONEVVISIBLE);
            this.mboundView11.setVisibility(vmIsLoadingVVISIBLEVGONE);
        }
        if ((dirtyFlags & 0x310L) != 0) {
            // api target 1

            this.mboundView10.setVisibility(vmIsVerifiedVVISIBLEVGONE);
            this.mboundView7.setVisibility(vmIsVerifiedJavaLangObjectNullVVISIBLEVGONE);
            this.mboundView8.setVisibility(vmIsVerifiedJavaLangObjectNullVGONEVVISIBLE);
            this.mboundView9.setVisibility(vmIsVerifiedVGONEVVISIBLE);
        }
        if ((dirtyFlags & 0x320L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, vmUserNameGetValue);
        }
        if ((dirtyFlags & 0x304L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, vmUserDetailsGetValue);
            this.mboundView3.setVisibility(vmUserDetailsEqualsJavaLangStringVGONEVVISIBLE);
        }
        if ((dirtyFlags & 0x302L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, vmMessageDateGetValue);
        }
        if ((dirtyFlags & 0x380L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, vmMessageGetDecryptedData);
        }
        if ((dirtyFlags & 0x200L) != 0) {
            // api target 1

            this.mboundView7.setOnClickListener(mCallback12);
        }
        if ((dirtyFlags & 0x301L) != 0) {
            // api target 1

            this.mboundView7.setEnabled(androidxDatabindingViewDataBindingSafeUnboxVmHasFilePathGetValue);
        }
        if ((dirtyFlags & 0x308L) != 0) {
            // api target 1

            this.progressBar.setVisibility(vmIsFileLoadingVVISIBLEVGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // vm != null
        boolean vmJavaLangObjectNull = false;
        // vm
        com.komandor.komandor.ui.message_info.MessageInfoViewModel vm = mVm;



        vmJavaLangObjectNull = (vm) != (null);
        if (vmJavaLangObjectNull) {


            vm.verifyCMS();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm.hasFilePath
        flag 1 (0x2L): vm.messageDate
        flag 2 (0x3L): vm.userDetails
        flag 3 (0x4L): vm.isFileLoading
        flag 4 (0x5L): vm.isVerified
        flag 5 (0x6L): vm.userName
        flag 6 (0x7L): vm.isLoading
        flag 7 (0x8L): vm.message
        flag 8 (0x9L): vm
        flag 9 (0xaL): null
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.GONE : V.VISIBLE
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.GONE : V.VISIBLE
        flag 12 (0xdL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isVerified.getValue()) ? V.GONE : V.VISIBLE
        flag 13 (0xeL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isVerified.getValue()) ? V.GONE : V.VISIBLE
        flag 14 (0xfL): vm.userDetails.getValue().equals("") ? V.GONE : V.VISIBLE
        flag 15 (0x10L): vm.userDetails.getValue().equals("") ? V.GONE : V.VISIBLE
        flag 16 (0x11L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isVerified.getValue()) ? V.VISIBLE : V.GONE
        flag 17 (0x12L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isVerified.getValue()) ? V.VISIBLE : V.GONE
        flag 18 (0x13L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 19 (0x14L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 20 (0x15L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isFileLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 21 (0x16L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isFileLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 22 (0x17L): vm.isVerified.getValue() == null ? V.VISIBLE : V.GONE
        flag 23 (0x18L): vm.isVerified.getValue() == null ? V.VISIBLE : V.GONE
        flag 24 (0x19L): vm.isVerified.getValue() == null ? V.GONE : V.VISIBLE
        flag 25 (0x1aL): vm.isVerified.getValue() == null ? V.GONE : V.VISIBLE
    flag mapping end*/
    //end
}