package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CertValidationBindingImpl extends CertValidationBinding implements com.komandor.komandor.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.pb_registration_progress_bar, 5);
        sViewsWithIds.put(R.id.tv_cert_validation_selected_certificate_text, 6);
        sViewsWithIds.put(R.id.til_cert_validation_password_layout, 7);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback4;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener tietCertValidationPasswordInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.password.getValue()
            //         is vm.password.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(tietCertValidationPasswordInput);
            // localize variables for thread safety
            // vm.password.getValue()
            java.lang.String vmPasswordGetValue = null;
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            com.komandor.komandor.ui.auth.cert_validation.CertValidationViewModel vm = mVm;
            // vm.password != null
            boolean vmPasswordJavaLangObjectNull = false;
            // vm.password
            androidx.lifecycle.MutableLiveData<java.lang.String> vmPassword = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {


                vmPassword = vm.getPassword();

                vmPasswordJavaLangObjectNull = (vmPassword) != (null);
                if (vmPasswordJavaLangObjectNull) {




                    vmPassword.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public CertValidationBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private CertValidationBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (com.google.android.material.button.MaterialButton) bindings[4]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.ProgressBar) bindings[5]
            , (com.google.android.material.textfield.TextInputEditText) bindings[3]
            , (com.google.android.material.textfield.TextInputLayout) bindings[7]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[6]
            );
        this.btnCertValidationLogin.setTag(null);
        this.llRegistrationLoadingContainer.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tietCertValidationPasswordInput.setTag(null);
        this.tvCertValidationSelectedCertificate.setTag(null);
        setRootTag(root);
        // listeners
        mCallback4 = new com.komandor.komandor.generated.callback.OnClickListener(this, 1);
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
        if (BR.vm == variableId) {
            setVm((com.komandor.komandor.ui.auth.cert_validation.CertValidationViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable com.komandor.komandor.ui.auth.cert_validation.CertValidationViewModel Vm) {
        this.mVm = Vm;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.vm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeVmIsLoading((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeVmPassword((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVmIsLoading(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmIsLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmPassword(androidx.lifecycle.MutableLiveData<java.lang.String> VmPassword, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        com.komandor.komandor.ui.auth.cert_validation.CertValidationViewModel vm = mVm;
        java.lang.String vmPasswordGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = false;
        int vmIsLoadingVVISIBLEVGONE = 0;
        java.lang.Boolean vmIsLoadingGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsLoading = null;
        java.lang.String vmGetSelectedCertificateClient = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> vmPassword = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (vm != null) {
                        // read vm.isLoading
                        vmIsLoading = vm.getIsLoading();
                    }
                    updateLiveDataRegistration(0, vmIsLoading);


                    if (vmIsLoading != null) {
                        // read vm.isLoading.getValue()
                        vmIsLoadingGetValue = vmIsLoading.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmIsLoadingGetValue);
                if((dirtyFlags & 0xdL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) {
                            dirtyFlags |= 0x20L;
                    }
                    else {
                            dirtyFlags |= 0x10L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
                    vmIsLoadingVVISIBLEVGONE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0xcL) != 0) {

                    if (vm != null) {
                        // read vm.getSelectedCertificateClient()
                        vmGetSelectedCertificateClient = vm.getSelectedCertificateClient();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (vm != null) {
                        // read vm.password
                        vmPassword = vm.getPassword();
                    }
                    updateLiveDataRegistration(1, vmPassword);


                    if (vmPassword != null) {
                        // read vm.password.getValue()
                        vmPasswordGetValue = vmPassword.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.btnCertValidationLogin.setOnClickListener(mCallback4);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.tietCertValidationPasswordInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, tietCertValidationPasswordInputandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            this.llRegistrationLoadingContainer.setVisibility(vmIsLoadingVVISIBLEVGONE);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tietCertValidationPasswordInput, vmPasswordGetValue);
        }
        if ((dirtyFlags & 0xcL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCertValidationSelectedCertificate, vmGetSelectedCertificateClient);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // vm != null
        boolean vmJavaLangObjectNull = false;
        // vm
        com.komandor.komandor.ui.auth.cert_validation.CertValidationViewModel vm = mVm;



        vmJavaLangObjectNull = (vm) != (null);
        if (vmJavaLangObjectNull) {


            vm.validateCertificate();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm.isLoading
        flag 1 (0x2L): vm.password
        flag 2 (0x3L): vm
        flag 3 (0x4L): null
        flag 4 (0x5L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 5 (0x6L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
    flag mapping end*/
    //end
}