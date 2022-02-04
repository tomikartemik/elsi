package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class RegistrationBindingImpl extends RegistrationBinding implements com.komandor.komandor.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.pb_registration_progress_bar, 7);
        sViewsWithIds.put(R.id.til_auth_phone, 8);
        sViewsWithIds.put(R.id.til_auth_code, 9);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView5;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener tietAuthCodeandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.smsCode
            //         is vm.setSmsCode((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(tietAuthCode);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            com.komandor.komandor.ui.auth.registration.RegistrationViewModel vm = mVm;
            // vm.smsCode
            java.lang.String vmSmsCode = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {




                vm.setSmsCode(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener tietAuthPhoneandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.phoneNumber
            //         is vm.setPhoneNumber((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(tietAuthPhone);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            com.komandor.komandor.ui.auth.registration.RegistrationViewModel vm = mVm;
            // vm.phoneNumber
            java.lang.String vmPhoneNumber = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {




                vm.setPhoneNumber(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public RegistrationBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private RegistrationBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (com.google.android.material.button.MaterialButton) bindings[4]
            , (com.google.android.material.button.MaterialButton) bindings[6]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.ProgressBar) bindings[7]
            , (com.google.android.material.textfield.TextInputEditText) bindings[3]
            , (com.google.android.material.textfield.TextInputEditText) bindings[2]
            , (com.google.android.material.textfield.TextInputLayout) bindings[9]
            , (com.google.android.material.textfield.TextInputLayout) bindings[8]
            );
        this.btnAuthCheckCode.setTag(null);
        this.btnAuthGetCode.setTag(null);
        this.llRegistrationLoadingContainer.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView5 = (android.widget.LinearLayout) bindings[5];
        this.mboundView5.setTag(null);
        this.tietAuthCode.setTag(null);
        this.tietAuthPhone.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.komandor.komandor.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
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
            setVm((com.komandor.komandor.ui.auth.registration.RegistrationViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable com.komandor.komandor.ui.auth.registration.RegistrationViewModel Vm) {
        this.mVm = Vm;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.vm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeVmIsWait((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeVmIsLoading((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeVmIsCodeSent((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 3 :
                return onChangeVmGetCodeButtonText((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVmIsWait(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmIsWait, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmIsLoading(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmIsLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmIsCodeSent(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmIsCodeSent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmGetCodeButtonText(androidx.lifecycle.MutableLiveData<java.lang.String> VmGetCodeButtonText, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
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
        com.komandor.komandor.ui.auth.registration.RegistrationViewModel vm = mVm;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsWait = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsCodeSentGetValue = false;
        java.lang.String vmGetCodeButtonTextGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = false;
        boolean VmIsWait1 = false;
        java.lang.Boolean vmIsCodeSentGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsWait = false;
        java.lang.String vmSmsCode = null;
        int vmIsCodeSentGBOTTOMGTOP = 0;
        int vmIsLoadingVVISIBLEVGONE = 0;
        int vmIsCodeSentVVISIBLEVGONE = 0;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsWaitGetValue = false;
        java.lang.Boolean vmIsLoadingGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsLoading = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsCodeSent = null;
        int gCENTERHORIZONTALVmIsCodeSentGBOTTOMGTOP = 0;
        java.lang.Boolean vmIsWaitGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> vmGetCodeButtonText = null;
        java.lang.String vmPhoneNumber = null;

        if ((dirtyFlags & 0x3fL) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (vm != null) {
                        // read vm.isWait
                        vmIsWait = vm.getIsWait();
                    }
                    updateLiveDataRegistration(0, vmIsWait);


                    if (vmIsWait != null) {
                        // read vm.isWait.getValue()
                        vmIsWaitGetValue = vmIsWait.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isWait.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmIsWaitGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmIsWaitGetValue);


                    // read !androidx.databinding.ViewDataBinding.safeUnbox(vm.isWait.getValue())
                    VmIsWait1 = !androidxDatabindingViewDataBindingSafeUnboxVmIsWaitGetValue;


                    // read androidx.databinding.ViewDataBinding.safeUnbox(!androidx.databinding.ViewDataBinding.safeUnbox(vm.isWait.getValue()))
                    androidxDatabindingViewDataBindingSafeUnboxVmIsWait = androidx.databinding.ViewDataBinding.safeUnbox(VmIsWait1);
            }
            if ((dirtyFlags & 0x30L) != 0) {

                    if (vm != null) {
                        // read vm.smsCode
                        vmSmsCode = vm.getSmsCode();
                        // read vm.phoneNumber
                        vmPhoneNumber = vm.getPhoneNumber();
                    }
            }
            if ((dirtyFlags & 0x32L) != 0) {

                    if (vm != null) {
                        // read vm.isLoading
                        vmIsLoading = vm.getIsLoading();
                    }
                    updateLiveDataRegistration(1, vmIsLoading);


                    if (vmIsLoading != null) {
                        // read vm.isLoading.getValue()
                        vmIsLoadingGetValue = vmIsLoading.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmIsLoadingGetValue);
                if((dirtyFlags & 0x32L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) {
                            dirtyFlags |= 0x200L;
                    }
                    else {
                            dirtyFlags |= 0x100L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
                    vmIsLoadingVVISIBLEVGONE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x34L) != 0) {

                    if (vm != null) {
                        // read vm.isCodeSent
                        vmIsCodeSent = vm.getIsCodeSent();
                    }
                    updateLiveDataRegistration(2, vmIsCodeSent);


                    if (vmIsCodeSent != null) {
                        // read vm.isCodeSent.getValue()
                        vmIsCodeSentGetValue = vmIsCodeSent.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isCodeSent.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmIsCodeSentGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmIsCodeSentGetValue);
                if((dirtyFlags & 0x34L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxVmIsCodeSentGetValue) {
                            dirtyFlags |= 0x80L;
                            dirtyFlags |= 0x800L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                            dirtyFlags |= 0x400L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isCodeSent.getValue()) ? G.BOTTOM : G.TOP
                    vmIsCodeSentGBOTTOMGTOP = ((androidxDatabindingViewDataBindingSafeUnboxVmIsCodeSentGetValue) ? (android.view.Gravity.BOTTOM) : (android.view.Gravity.TOP));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isCodeSent.getValue()) ? V.VISIBLE : V.GONE
                    vmIsCodeSentVVISIBLEVGONE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsCodeSentGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));


                    // read (G.CENTER_HORIZONTAL) | (androidx.databinding.ViewDataBinding.safeUnbox(vm.isCodeSent.getValue()) ? G.BOTTOM : G.TOP)
                    gCENTERHORIZONTALVmIsCodeSentGBOTTOMGTOP = (android.view.Gravity.CENTER_HORIZONTAL) | (vmIsCodeSentGBOTTOMGTOP);
            }
            if ((dirtyFlags & 0x38L) != 0) {

                    if (vm != null) {
                        // read vm.getCodeButtonText
                        vmGetCodeButtonText = vm.getGetCodeButtonText();
                    }
                    updateLiveDataRegistration(3, vmGetCodeButtonText);


                    if (vmGetCodeButtonText != null) {
                        // read vm.getCodeButtonText.getValue()
                        vmGetCodeButtonTextGetValue = vmGetCodeButtonText.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x34L) != 0) {
            // api target 1

            this.btnAuthCheckCode.setVisibility(vmIsCodeSentVVISIBLEVGONE);
            this.mboundView5.setGravity(gCENTERHORIZONTALVmIsCodeSentGBOTTOMGTOP);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            this.btnAuthGetCode.setEnabled(androidxDatabindingViewDataBindingSafeUnboxVmIsWait);
        }
        if ((dirtyFlags & 0x20L) != 0) {
            // api target 1

            this.btnAuthGetCode.setOnClickListener(mCallback1);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.tietAuthCode, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, tietAuthCodeandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.tietAuthPhone, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, tietAuthPhoneandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x38L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnAuthGetCode, vmGetCodeButtonTextGetValue);
        }
        if ((dirtyFlags & 0x32L) != 0) {
            // api target 1

            this.llRegistrationLoadingContainer.setVisibility(vmIsLoadingVVISIBLEVGONE);
        }
        if ((dirtyFlags & 0x30L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tietAuthCode, vmSmsCode);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tietAuthPhone, vmPhoneNumber);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // vm != null
        boolean vmJavaLangObjectNull = false;
        // vm
        com.komandor.komandor.ui.auth.registration.RegistrationViewModel vm = mVm;



        vmJavaLangObjectNull = (vm) != (null);
        if (vmJavaLangObjectNull) {


            vm.getCode();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm.isWait
        flag 1 (0x2L): vm.isLoading
        flag 2 (0x3L): vm.isCodeSent
        flag 3 (0x4L): vm.getCodeButtonText
        flag 4 (0x5L): vm
        flag 5 (0x6L): null
        flag 6 (0x7L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isCodeSent.getValue()) ? G.BOTTOM : G.TOP
        flag 7 (0x8L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isCodeSent.getValue()) ? G.BOTTOM : G.TOP
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 9 (0xaL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isCodeSent.getValue()) ? V.VISIBLE : V.GONE
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isCodeSent.getValue()) ? V.VISIBLE : V.GONE
    flag mapping end*/
    //end
}