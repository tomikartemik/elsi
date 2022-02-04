package com.komandor.komandor.databinding;
import com.komandor.komandor.R;
import com.komandor.komandor.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ContactInfoBindingImpl extends ContactInfoBinding implements com.komandor.komandor.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_contact_info_avatar, 15);
        sViewsWithIds.put(R.id.contactInfoScreenEmptyLayout, 16);
        sViewsWithIds.put(R.id.mockImageView, 17);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    private final android.widget.ScrollView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView10;
    @NonNull
    private final android.widget.TextView mboundView11;
    @NonNull
    private final android.widget.LinearLayout mboundView13;
    @NonNull
    private final android.widget.FrameLayout mboundView14;
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView5;
    @NonNull
    private final android.widget.TextView mboundView6;
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView7;
    @NonNull
    private final android.widget.TextView mboundView8;
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView9;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback5;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ContactInfoBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }
    private ContactInfoBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.LinearLayout) bindings[16]
            , (com.google.android.material.button.MaterialButton) bindings[12]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[17]
            );
        this.contactInfoScreenWriteMessageButton.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.ScrollView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView10 = (android.widget.TextView) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (android.widget.TextView) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView13 = (android.widget.LinearLayout) bindings[13];
        this.mboundView13.setTag(null);
        this.mboundView14 = (android.widget.FrameLayout) bindings[14];
        this.mboundView14.setTag(null);
        this.mboundView2 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (android.widget.TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.mboundView9 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[9];
        this.mboundView9.setTag(null);
        setRootTag(root);
        // listeners
        mCallback5 = new com.komandor.komandor.generated.callback.OnClickListener(this, 1);
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
            setVm((com.komandor.komandor.ui.contact_info.ContactInfoViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable com.komandor.komandor.ui.contact_info.ContactInfoViewModel Vm) {
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
                return onChangeVmIsWaiting((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeVmIsLoading((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeVmContact((androidx.lifecycle.MutableLiveData<com.komandor.komandor.data.database.contacts.Contact>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVmIsWaiting(androidx.lifecycle.MutableLiveData<java.lang.Boolean> VmIsWaiting, int fieldId) {
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
    private boolean onChangeVmContact(androidx.lifecycle.MutableLiveData<com.komandor.komandor.data.database.contacts.Contact> VmContact, int fieldId) {
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
        int vmContactType = 0;
        java.lang.String vmContactTypeConstNOTYPEVmContactContactNameVmContactName = null;
        com.komandor.komandor.ui.contact_info.ContactInfoViewModel vm = mVm;
        java.lang.String vmContactSurname = null;
        java.lang.String vmContactPatronymic = null;
        java.lang.String vmContactContactName = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue = false;
        int vmIsLoadingVGONEVVISIBLE = 0;
        java.lang.Boolean vmIsWaitingGetValue = null;
        int vmContactTypeConstNOTYPEVGONEVVISIBLE = 0;
        int vmIsWaitingVVISIBLEVGONE = 0;
        java.lang.String vmContactTitle = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxVmIsWaitingGetValue = false;
        com.komandor.komandor.data.database.contacts.Contact vmContactGetValue = null;
        int vmIsLoadingVVISIBLEVGONE = 0;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsWaiting = null;
        java.lang.String vmContactPhone = null;
        int vmContactTypeConstLPVVISIBLEVGONE = 0;
        boolean vmContactTypeConstLP = false;
        java.lang.Boolean vmIsLoadingGetValue = null;
        java.lang.String vmContactName = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> vmIsLoading = null;
        androidx.lifecycle.MutableLiveData<com.komandor.komandor.data.database.contacts.Contact> vmContact = null;
        java.lang.String vmContactCompany = null;
        boolean vmContactTypeConstNOTYPE = false;
        boolean VmContactTypeConstNOTYPE1 = false;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (vm != null) {
                        // read vm.isWaiting
                        vmIsWaiting = vm.getIsWaiting();
                    }
                    updateLiveDataRegistration(0, vmIsWaiting);


                    if (vmIsWaiting != null) {
                        // read vm.isWaiting.getValue()
                        vmIsWaitingGetValue = vmIsWaiting.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isWaiting.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxVmIsWaitingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(vmIsWaitingGetValue);
                if((dirtyFlags & 0x19L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxVmIsWaitingGetValue) {
                            dirtyFlags |= 0x1000L;
                    }
                    else {
                            dirtyFlags |= 0x800L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isWaiting.getValue()) ? V.VISIBLE : V.GONE
                    vmIsWaitingVVISIBLEVGONE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsWaitingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x1aL) != 0) {

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
                if((dirtyFlags & 0x1aL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) {
                            dirtyFlags |= 0x100L;
                            dirtyFlags |= 0x4000L;
                    }
                    else {
                            dirtyFlags |= 0x80L;
                            dirtyFlags |= 0x2000L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.GONE : V.VISIBLE
                    vmIsLoadingVGONEVVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
                    vmIsLoadingVVISIBLEVGONE = ((androidxDatabindingViewDataBindingSafeUnboxVmIsLoadingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (vm != null) {
                        // read vm.contact
                        vmContact = vm.getContact();
                    }
                    updateLiveDataRegistration(2, vmContact);


                    if (vmContact != null) {
                        // read vm.contact.getValue()
                        vmContactGetValue = vmContact.getValue();
                    }


                    if (vmContactGetValue != null) {
                        // read vm.contact.getValue().type
                        vmContactType = vmContactGetValue.getType();
                        // read vm.contact.getValue().surname
                        vmContactSurname = vmContactGetValue.getSurname();
                        // read vm.contact.getValue().patronymic
                        vmContactPatronymic = vmContactGetValue.getPatronymic();
                        // read vm.contact.getValue().title
                        vmContactTitle = vmContactGetValue.getTitle();
                        // read vm.contact.getValue().phone
                        vmContactPhone = vmContactGetValue.getPhone();
                        // read vm.contact.getValue().company
                        vmContactCompany = vmContactGetValue.getCompany();
                    }


                    // read vm.contact.getValue().type == Const.LP
                    vmContactTypeConstLP = (vmContactType) == (com.komandor.komandor.utils.Constants.LP);
                    // read vm.contact.getValue().type == Const.NO_TYPE
                    vmContactTypeConstNOTYPE = (vmContactType) == (com.komandor.komandor.utils.Constants.NO_TYPE);
                    // read vm.contact.getValue().type != Const.NO_TYPE
                    VmContactTypeConstNOTYPE1 = (vmContactType) != (com.komandor.komandor.utils.Constants.NO_TYPE);
                if((dirtyFlags & 0x1cL) != 0) {
                    if(vmContactTypeConstLP) {
                            dirtyFlags |= 0x10000L;
                    }
                    else {
                            dirtyFlags |= 0x8000L;
                    }
                }
                if((dirtyFlags & 0x1cL) != 0) {
                    if(vmContactTypeConstNOTYPE) {
                            dirtyFlags |= 0x40L;
                            dirtyFlags |= 0x400L;
                    }
                    else {
                            dirtyFlags |= 0x20L;
                            dirtyFlags |= 0x200L;
                    }
                }


                    // read vm.contact.getValue().type == Const.LP ? V.VISIBLE : V.GONE
                    vmContactTypeConstLPVVISIBLEVGONE = ((vmContactTypeConstLP) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                    // read vm.contact.getValue().type == Const.NO_TYPE ? V.GONE : V.VISIBLE
                    vmContactTypeConstNOTYPEVGONEVVISIBLE = ((vmContactTypeConstNOTYPE) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
            }
        }
        // batch finished

        if ((dirtyFlags & 0x40L) != 0) {

                if (vmContactGetValue != null) {
                    // read vm.contact.getValue().contactName
                    vmContactContactName = vmContactGetValue.getContactName();
                }
        }
        if ((dirtyFlags & 0x20L) != 0) {

                if (vmContactGetValue != null) {
                    // read vm.contact.getValue().name
                    vmContactName = vmContactGetValue.getName();
                }
        }

        if ((dirtyFlags & 0x1cL) != 0) {

                // read vm.contact.getValue().type == Const.NO_TYPE ? vm.contact.getValue().contactName : vm.contact.getValue().name
                vmContactTypeConstNOTYPEVmContactContactNameVmContactName = ((vmContactTypeConstNOTYPE) ? (vmContactContactName) : (vmContactName));
        }
        // batch finished
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            this.contactInfoScreenWriteMessageButton.setEnabled(VmContactTypeConstNOTYPE1);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView10, vmContactTitle);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView11, vmContactPhone);
            this.mboundView2.setVisibility(vmContactTypeConstNOTYPEVGONEVVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, vmContactSurname);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, vmContactTypeConstNOTYPEVmContactContactNameVmContactName);
            this.mboundView5.setVisibility(vmContactTypeConstNOTYPEVGONEVVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, vmContactPatronymic);
            this.mboundView7.setVisibility(vmContactTypeConstLPVVISIBLEVGONE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView8, vmContactCompany);
            this.mboundView9.setVisibility(vmContactTypeConstLPVVISIBLEVGONE);
        }
        if ((dirtyFlags & 0x10L) != 0) {
            // api target 1

            this.contactInfoScreenWriteMessageButton.setOnClickListener(mCallback5);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            this.mboundView1.setVisibility(vmIsLoadingVGONEVVISIBLE);
            this.mboundView14.setVisibility(vmIsLoadingVVISIBLEVGONE);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            this.mboundView13.setVisibility(vmIsWaitingVVISIBLEVGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // vm != null
        boolean vmJavaLangObjectNull = false;
        // vm
        com.komandor.komandor.ui.contact_info.ContactInfoViewModel vm = mVm;



        vmJavaLangObjectNull = (vm) != (null);
        if (vmJavaLangObjectNull) {


            vm.writeMessage();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm.isWaiting
        flag 1 (0x2L): vm.isLoading
        flag 2 (0x3L): vm.contact
        flag 3 (0x4L): vm
        flag 4 (0x5L): null
        flag 5 (0x6L): vm.contact.getValue().type == Const.NO_TYPE ? vm.contact.getValue().contactName : vm.contact.getValue().name
        flag 6 (0x7L): vm.contact.getValue().type == Const.NO_TYPE ? vm.contact.getValue().contactName : vm.contact.getValue().name
        flag 7 (0x8L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.GONE : V.VISIBLE
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.GONE : V.VISIBLE
        flag 9 (0xaL): vm.contact.getValue().type == Const.NO_TYPE ? V.GONE : V.VISIBLE
        flag 10 (0xbL): vm.contact.getValue().type == Const.NO_TYPE ? V.GONE : V.VISIBLE
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isWaiting.getValue()) ? V.VISIBLE : V.GONE
        flag 12 (0xdL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isWaiting.getValue()) ? V.VISIBLE : V.GONE
        flag 13 (0xeL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 14 (0xfL): androidx.databinding.ViewDataBinding.safeUnbox(vm.isLoading.getValue()) ? V.VISIBLE : V.GONE
        flag 15 (0x10L): vm.contact.getValue().type == Const.LP ? V.VISIBLE : V.GONE
        flag 16 (0x11L): vm.contact.getValue().type == Const.LP ? V.VISIBLE : V.GONE
    flag mapping end*/
    //end
}